package com.creditease.ns4.gear.watchdog.common.template;import com.creditease.ns.log.NsLog;import com.creditease.ns4.gear.watchdog.common.log.NsLogger;import freemarker.template.Configuration;import freemarker.template.TemplateException;import java.io.File;import java.io.IOException;import java.io.StringWriter;import java.io.Writer;import java.util.Locale;/** * @author 杨红岩 * @description 模板工具 * @date 2019/3/5 */public class Template {    private static final NsLog logger = NsLogger.getWatchdogLogger();    private static Configuration configuration = new Configuration();    //初始化模板配置    static {        try {            String path = ClassLoader.getSystemResource("template").getPath();            configuration.setDirectoryForTemplateLoading(new File(path));        } catch (IOException e) {            logger.error("初始化freemarker模板错误：{} {}", e.getMessage(), e);        }        configuration.setClassicCompatible(true);        configuration.setEncoding(Locale.getDefault(), "UTF-8");    }    /**     * 渲染模板     *     * @param templateName 模板名称     * @param dataModel    数据模型     * @return html     */    public static String render(String templateName, Object dataModel) {        Writer out = null;        try {            freemarker.template.Template temp = configuration.getTemplate(templateName);            out = new StringWriter(2048);            temp.process(dataModel, out);            String html = out.toString();            return html;        } catch (IOException e) {            logger.error("渲染模板错误：{} {}", e.getMessage(), e);        } catch (TemplateException e) {            logger.error("渲染模板错误：{} {}", e.getMessage(), e);        } finally {            if (out != null) {                try {                    out.close();                    out = null;                } catch (IOException e) {                    //忽略                }            }        }        return null;    }    public static void main(String[] args) {        String path = ClassLoader.getSystemResource("template").getPath();        System.out.println(path);    }}