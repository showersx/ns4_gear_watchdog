package com.creditease.ns4.gear.watchdog.monitor.process.config;import com.creditease.ns.log.NsLog;import com.creditease.ns4.gear.watchdog.common.PropertiesUtil;import com.creditease.ns4.gear.watchdog.common.log.NsLogger;import com.creditease.ns4.gear.watchdog.monitor.process.constant.PROCES;/** * @author 杨工岩 * @description NS4 App 配置信息 * @date 2019/2/22 */public class NS4AppConf implements Cloneable {    private static final NsLog logger = NsLogger.getWatchdogLogger();    private String rootPath;    private String jvmOpts;    private String appName;    private String serverMain;    public NS4AppConf() {        this.jvmOpts = PropertiesUtil.getValue("ns4.app.jvm.opts");        this.rootPath = System.getProperty("user.dir");        this.appName = PropertiesUtil.getValue("ns4.app.server.name");        this.serverMain = PropertiesUtil.getValue("ns4.app.server.main");        if (this.appName != null && !"".equals(this.appName.trim())) {            this.appName = this.appName.replaceAll("( +)", "");        } else {            this.appName = PROCES.APP_NAME_DEFAULT;        }        logger.info("NS4 APP 配置信息 - [ROOT    PATH\t{}]", this.rootPath);        logger.info("NS4 APP 配置信息 - [JVM     OPTS\t{}]", this.jvmOpts);        logger.info("NS4 APP 配置信息 - [SERVER  MAIN\t{}]", this.serverMain);        logger.info("NS4 APP 配置信息 - [APP     NAME\t{}]", this.appName);    }    public String getJvmOpts() {        return jvmOpts;    }    public String getRootPath() {        return rootPath;    }    public String getAppName() {        return appName;    }    public String getServerMain() {        return serverMain;    }    @Override    public Object clone() {        NS4AppConf ns4AppConf = null;        try {            ns4AppConf = (NS4AppConf) super.clone();        } catch (CloneNotSupportedException e) {        }        return ns4AppConf;    }}