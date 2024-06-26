package com.cc.security.filters;

import com.cc.core.constants.CoreReturnMessage;
import com.cc.core.domain.ref.PlanoUsuario;
import com.cc.core.domain.ref.TipoUsuario;
import com.cc.core.model.MessageDto;
import com.cc.model.authentication.entity.LoginDto;
import com.cc.security.authentication.Authentication;
import com.cc.security.authentication.LoginFactory;
import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

@Provider
@Authentication
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Context
	private ResourceInfo resourceInfo;


	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		LoginDto login = (LoginDto) requestContext.getProperty(LoginFactory.LOGIN_PROPERTY);
		Authentication annotation = getAuthenticationAnnotation(resourceInfo);

		if (!isLoginValid(login)) {
			throwAuthException(CoreReturnMessage.LOGIN_NOT_INFORMED_ERROR);
		}

		boolean allowed = verifyToken(annotation, login);
		if (!allowed) {
			throwAuthException(CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR);
		}
	}

	private void throwAuthException(String message) {
		throw new WebApplicationException(
				Response.status(Status.FORBIDDEN)
						.entity(new MessageDto(message))
						.type(MediaType.APPLICATION_JSON)
						.build());
	}

	private boolean verifyToken(Authentication annotation, LoginDto login) {
		boolean allowed = true;
		if(Objects.nonNull(annotation)) {
			allowed = isPlanoAllowedForResource(login, annotation)
					&& isPlanoExpired(login, annotation)
					&& isTipoUsuarioAllowed(login, annotation);
		}
		return allowed;
	}

	private boolean isTipoUsuarioAllowed(LoginDto login, Authentication annotation) {
		boolean result = true;

		TipoUsuario[] validProfile = annotation.types();

		if (Objects.nonNull(validProfile)) {
			TipoUsuario loggedType = TipoUsuario.fromStr(login.getTipo().getKey());

			if (!TipoUsuario.MASTER.equals(loggedType) && !ArrayUtils.contains(validProfile, loggedType)) {
				log.info(String.format("Tipo do usuário não autorizado | Necessários: %s | Usuario: %s ", Arrays.toString(validProfile), loggedType));
				result = false;
			}
		}
		return result;
	}

	private boolean isLoginValid(LoginDto login) {
        return Objects.nonNull(login);
    }

	private boolean isPlanoExpired(LoginDto login, Authentication annotation) {
		PlanoUsuario methodProfile = annotation.plano();

		if(!PlanoUsuario.FREE.equals(methodProfile)) {
            return BooleanUtils.isFalse(login.getInformacaoPlano().getExpirado());
		}

		return true;
	}

	private Authentication getAuthenticationAnnotation(ResourceInfo resourceInfo) {
		Authentication annotation = resourceInfo.getResourceMethod().getAnnotation(Authentication.class);
		if (Objects.isNull(annotation)) {
			annotation = resourceInfo.getResourceClass().getAnnotation(Authentication.class);
		}
		return annotation;
	}

	private boolean isPlanoAllowedForResource(LoginDto login, Authentication annotation) {
		boolean result = true;

		PlanoUsuario validProfile = annotation.plano();

		if (Objects.nonNull(validProfile)) {
			result = validProfile.isAllowed(login.getInformacaoPlano().getPlanoUsuario().getKey());
			if (!result) {
				log.info(String.format(CoreReturnMessage.LOWER_SUBSCRIPTION_ERROR, validProfile));
			}
		}
		return result;
	}




}

