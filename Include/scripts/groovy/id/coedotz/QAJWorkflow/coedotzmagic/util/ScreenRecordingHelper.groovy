package id.coedotz.QAJWorkflow.coedotzmagic.util

import java.awt.AWTException
import java.awt.GraphicsConfiguration
import java.awt.Rectangle
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

import static org.monte.media.AudioFormatKeys.*
import static org.monte.media.VideoFormatKeys.*
import static org.monte.media.FormatKeys.MediaType.*
import org.monte.media.Format
import org.monte.media.Registry
import org.monte.media.math.Rational
import org.monte.media.FormatKeys.MediaType
import org.monte.screenrecorder.ScreenRecorder

import java.awt.Dimension
import java.awt.GraphicsEnvironment
import java.awt.Toolkit as Toolkit

import com.kms.katalon.core.setting.BundleSettingStore
import org.apache.commons.lang3.StringUtils

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ScreenRecordingHelper extends ScreenRecorder {

	private static final Logger LOG = LogManager.getLogger()
	
	//static BundleSettingStore bundleSetting
	//static String FORMATMEDIA

	public static final String USER_DIR = "user.dir"
	public static final String SCREENSRECORD_FOLDER = "ScreenRecording"

	private static ScreenRecorder screenRecorder
	private String name;

	private ScreenRecordingHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
	throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {
		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory.");
		}

		def util = new Util()

		return new File(movieFolder, name + "-" + util.performGetDateTime() + "." + Registry.getInstance().getExtension(fileFormat));
	}
	
	/* ------------------------------------------------------------------------- */
	
	/**
	 * <b>startRecording()</b>
	 * digunakan untuk memulai Screen Recording
	 *
	 * @since 1.0
	 */
	static void startRecording() throws Exception {
		File file = new File(System.getProperty(USER_DIR) + File.separator + SCREENSRECORD_FOLDER)

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize()
		int width = screenSize.width
		int height = screenSize.height

		Rectangle captureSize = new Rectangle(0, 0, width, height);
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()

		try {
			screenRecorder = new ScreenRecordingHelper(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_QUICKTIME_JPEG,
				CompressorNameKey, ENCODING_QUICKTIME_JPEG,
				DepthKey, 24, FrameRateKey, Rational.valueOf(15),
				QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
				FrameRateKey, Rational.valueOf(30)),
				null, file, "MyTesting")
			
			screenRecorder.start()
		} catch (final IOException | AWTException e) {
			LOG.error(e)
		}

		//bundleSetting = new BundleSettingStore(RunConfiguration.getProjectDir(), 'id.coedotz.QAJWorkflow.coedotzmagic', true)
		//FORMATMEDIA = bundleSetting.getString('formatMedia', '')

		//		Format fileFormat
		//		Format screenFormat
		//
		//		switch (FORMATMEDIA.toLowerCase()) {
		//			case 'mp4':
		//				fileFormat = new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME)
		//				screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_QUICKTIME_JPEG,
		//						CompressorNameKey, ENCODING_QUICKTIME_JPEG, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
		//						QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60)
		//				break
		//			case 'avi':
		//				fileFormat = new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI)
		//				screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
		//						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
		//						QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60)
		//				break
		//			case 'mov':
		//				fileFormat = new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME)
		//				screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_QUICKTIME_JPEG,
		//						CompressorNameKey, ENCODING_QUICKTIME_JPEG, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
		//						QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60)
		//				break
		//			case '3gp':
		//				fileFormat = new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, "video/3gpp")
		//				screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
		//						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
		//						QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60)
		//				break
		//			default:
		//				throw new IllegalArgumentException("Unsupported format: " + FORMATMEDIA)
		//		}
		//
		//		this.screenRecorder = new ScreenRecordingHelper(gc, captureSize, fileFormat, screenFormat,
		//				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
		//				null, file, "MyTesting")
	}

	/**
	 * <b>stopRecording()</b>
	 * digunakan untuk menghentikan Screen Recording
	 *
	 * @since 1.0
	 */
	static void stopRecording() throws Exception {
		try {
			screenRecorder.stop()
		} catch (final IOException | AWTException e) {
			LOG.error(e)
		}
	}
}