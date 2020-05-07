package com.jeesite.modules.unit;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author jiawei.liang
 * @Date 2020/4/23 15:19
 * @Version 1.0
 */
public class QuartzContextListener implements ServletContextListener {

    /*
     * 测试代码写得随便
     *
     * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        WebApplicationContext webApplicationContext = (WebApplicationContext) arg0
                .getServletContext()
                .getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        org.quartz.impl.StdScheduler startQuertz = (org.quartz.impl.StdScheduler) webApplicationContext
                .getBean("startQuertz");
        if(startQuertz != null) {
            startQuertz.shutdown();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //不做任何事情
    }

}