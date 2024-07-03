package com.urubu.core.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.urubu.core.exceptions.BusinessException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import static java.awt.SystemColor.text;

public class QRCodeUtils {

	public static String generateQRCode(String link) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, 300, 300);

			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ImageIO.write(bufferedImage, "PNG", outputStream);

			byte[] pngData = outputStream.toByteArray();

			// Codificar o array de bytes em Base64
			return Base64.getEncoder().encodeToString(pngData);
		} catch (Exception e) {
			throw new BusinessException("QRCODE ERROR");
		}
	}
}
