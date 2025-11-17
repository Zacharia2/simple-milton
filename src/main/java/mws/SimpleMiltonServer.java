package mws;

import java.io.File;

import io.milton.config.HttpManagerBuilder;
import io.milton.http.HttpManager;
import fs.FileSystemResourceFactory;
import io.milton.http.fs.NullSecurityManager;
import io.milton.simpleton.SimpletonServer;

public class SimpleMiltonServer {
	static int port = 8080;
	static String homeFolder = "D:\\";

	public static void main(String[] args) {
//		一个ResourceFactory、一个HttpManagerBuilder、一个SimpletonServer
		FileSystemResourceFactory resourceFactory = new FileSystemResourceFactory(new File(homeFolder),
				new NullSecurityManager(), "/");
		resourceFactory.getContentService();
		resourceFactory.setAllowDirectoryBrowsing(true);
		HttpManagerBuilder b = new HttpManagerBuilder();
		b.setEnableFormAuth(false);
		b.setResourceFactory(resourceFactory);
		HttpManager httpManager = b.buildHttpManager();
		SimpletonServer ss = new SimpletonServer(httpManager, b.getOuterWebdavResponseHandler(), 100, 10);
		ss.setHttpPort(port);
		ss.start();
	}

}
