package com.jinfang.graduactionproject.test.office;

import org.junit.Before;

public class TranslationTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("contentEn", "In order to let the main process read the configuration file again, it should send a HUP signal to the main process. Once the main process receives the signal to reload the configuration file, it will check the validity of the configuration file syntax, and then try to apply the new configuration, that is, to open a new log file and a new socket If it fails, it will roll back the configuration changes and continue to use the old configuration. If successful, it will open a new worker process and send a message to the old one to let them close gracefully. After receiving the shutdown signal, the old worker process will not receive new requests. If there are existing requests being processed, it will close after the current request is processed. If there is no request, it will close after the current request is processed Is being processed directly.");
        params.put("contentCn", "为了让主进程重新读取配置文件，应该向主进程发送一个HUP信号，主进程一旦接收到重新加载配置的的信号，它就检查配置文件语法的有效性，然后试图应用新的配置，即打开新的日志文件和新的socket 监听，如果失败，它将回滚配置更改并继续使用旧的配置，如果成功了，它开启新的工作进程，并给旧的工作进程发消息让它们优雅的关闭，旧的工作进程接收到关闭信号后，不再接收新的请求，如果已有请求正在处理，等当前请求处理完毕后关闭，如果没有请求正在处理，则直接关闭。");

        sourceFileName = "18-translation.docx";
        targetFileName = "18-translation-" + System.currentTimeMillis() + ".docx";
    }

}
