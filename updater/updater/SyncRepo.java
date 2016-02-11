package updater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FileUtils;

import file_ose.FileHandle;
import network_ose.DownloadOSE;
import network_ose.ProxyOSE;
import network_ose.SSLOSE;
import zip_ose.UnZipOSE;

public class SyncRepo {

	private static String currentPath;
	private static String updateDirectory;
	private static String tempDirectoy;

	private static String readmeGit = "https://raw.githubusercontent.com/ibusmo/CMS_KBANK/master/README.md";
	private static String prjGit = "https://github.com/ibusmo/CMS_KBANK/archive/master.zip";

	public void sync() {
		try {
			enviConf();
			System.out.println("Environment Config -Pass");
		} catch (KeyManagementException e) {
			System.out.println("Environment Config -Fail - KeyManagementException");
			return;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Environment Config -Fail - NoSuchAlgorithmException");
			return;
		}
		System.out.println("------------------------------------------------------------------------------------");

		boolean isCurrentVersion = true;
		try {
			isCurrentVersion = isCurrentVersion();
			System.out.println("Version Check -Pass");
		} catch (IOException e) {
			System.out.println("Version Check -Fail - IOException");
			return;
		}
		System.out.println("------------------------------------------------------------------------------------");

		boolean isPullSuccess = false;
		if (!isCurrentVersion) {
			try {
				isPullSuccess = pullGitAndUnzip();
				System.out.println("Pull Repository -Pass");
			} catch (IOException e) {
				System.out.println("Pull Repository -Fail - IOException");
				return;
			}
		} else {
			System.out.println("Not Pull Repository -Pass");
		}
		System.out.println("------------------------------------------------------------------------------------");
		
		//isPullSuccess = true;
		if (isPullSuccess) {

			try {
				File folder = new File(tempDirectoy);
				File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < listOfFiles.length; i++) {

					if (listOfFiles[i].isFile()) {
						// System.out.println("File " +
						// listOfFiles[i].getName());
						copyFile(listOfFiles[i].getName(), listOfFiles[i].getName());

					} else if (listOfFiles[i].isDirectory()) {
						// System.out.println("Directory " +
						// listOfFiles[i].getName());
						if (listOfFiles[i].getName().contains("keywordscms")
								|| listOfFiles[i].getName().contains("updater")
								|| listOfFiles[i].getName().contains("src")) {
							System.out.println("skip D - " + tempDirectoy + "\\" + listOfFiles[i].getName());
						} else {
							copyDir(listOfFiles[i].getName(), listOfFiles[i].getName());
						}
					}

				}
				System.out.println("Update changes -Pass -" + listOfFiles.length);
			} catch (IOException e) {
				System.out.println("Update changes -Fail");
				e.printStackTrace();
			}
		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------");
	}

	private boolean pullGitAndUnzip() throws IOException {
		String prjGitItem = updateDirectory + "\\CMS_KBANK.zip";
		new DownloadOSE().storeItem(prjGit, prjGitItem);
		System.out.println("Store at Git Prj \t: " + prjGitItem);
		System.out.println("------------------------------------------------------------------------------------");
		new UnZipOSE().unZip(prjGitItem, updateDirectory);
		System.out.println("------------------------------------------------------------------------------------");
		return true;
	}

	private boolean isCurrentVersion() throws IOException {
		String versionOnline = readmeGit;
		String locaOoutputPath = updateDirectory + "\\readme.md";

		System.out.println("OnlinePath \t: " + versionOnline);
		System.out.println("LocalPath \t: " + locaOoutputPath);
		System.out.println("------------------------------------------------------------------------------------");

		String readmeOnline = new DownloadOSE().getStringItem(versionOnline);
		String readmeLocal = new FileHandle(locaOoutputPath).read();
		String readmeOnlineClean = cleanString(readmeOnline);
		String readmeLocalClean = cleanString(readmeLocal);

		System.out.println("readmeOnline \t: " + readmeOnline);
		System.out.println("readmeLocal \t: " + readmeLocal);
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("readmeOnline cut \t: " + readmeOnlineClean);
		System.out.println("readmeLocal  cut \t: " + readmeLocalClean);
		System.out.println("------------------------------------------------------------------------------------");

		boolean isCurrentVersion = true;
		if (readmeOnlineClean.contains(readmeLocalClean) && readmeLocalClean.length() > 5) {
			System.out.println("Same version");
			isCurrentVersion = true;
		} else {
			System.out.println("Difference version - Replace by newer");
			isCurrentVersion = false;
			new DownloadOSE().storeItem(versionOnline, locaOoutputPath);
			System.out.println("Store at readme.md \t: " + locaOoutputPath);
		}
		System.out.println("------------------------------------------------------------------------------------");

		return isCurrentVersion;
	}

	private void enviConf() throws KeyManagementException, NoSuchAlgorithmException {
		new SSLOSE().clear();
		new ProxyOSE().setProxy("172.16.222.5", "9980");
		currentPath = System.getProperty("user.dir");
		updateDirectory = currentPath + "\\..\\update";
		tempDirectoy = currentPath + "\\..\\update\\CMS_KBANK-master";
		System.out.println("currentPath : " + currentPath);
	}

	private String cleanString(String str) {
		return str.replace("\n", "").replace("\r", "");
	}

	private void copyFile(String src, String des) throws IOException {
		System.out.print("copy F - " + tempDirectoy + "\\" + src);
		System.out.print("\t - to - ");
		System.out.println(currentPath + "\\" + des);
		Files.copy(new File(tempDirectoy + "\\" + src).toPath(), new File(currentPath + "\\" + des).toPath(),
				StandardCopyOption.REPLACE_EXISTING);
	}

	private void copyDir(String src, String des) throws IOException {
		System.out.print("copy D - " + tempDirectoy + "\\" + src);
		System.out.print("\t - to - ");
		System.out.println(currentPath + "\\" + des);
		FileUtils.copyDirectory(new File(tempDirectoy + "\\" + src), new File(currentPath + "\\" + des));
	}

}
