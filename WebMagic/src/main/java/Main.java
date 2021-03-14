import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

public class Main implements  PageProcessor{

	private Site site = Site.me().setRetryTimes(3000);

	@Override
	public void process(Page page) {
		page.putField("Raw",page.getBytes());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		HttpClientDownloader downloader = new HttpClientDownloader();
		downloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",8889)));
		Spider.create(new Main()).addUrl("https://openaccess.thecvf.com/content/WACV2021/papers/Fortin_Towards_Contextual_Learning_in_Few-Shot_Object_Classification_WACV_2021_paper.pdf")
				.addPipeline(new MyPipeline())
				.setDownloader(downloader)
				.run();
	}
}
