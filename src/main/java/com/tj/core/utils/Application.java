package com.tj.core.utils;

import com.tj.sys.Entity.SysAuthFunc;
import com.tj.sys.service.FuncListService;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;


public class Application {
	
	
	private static Application application = null;
	public static final ThreadLocal<Application> session = new ThreadLocal<Application>();

	public static Application getInstance() {
		application = session.get();
		if (application == null) {
			application = new Application();
			session.set(application);
		}
		return application;
	}
	
	/**
	 * 初始化启动时候加载全部全局对象
	 * @param ctx
	 * @param servletContext
	 */
	public void runALL(ApplicationContext ctx, ServletContext servletContext) {
		this.runFuncMap(ctx, servletContext);
	}


	/**
	 * 加载所有的函数id与函数名称
	 * @param ctx
	 * @param servletContext
	 */
	private void runFuncMap(ApplicationContext ctx, ServletContext servletContext) {
		FuncListService funcListService  = (FuncListService) ctx.getBean("funcListService");
		List<SysAuthFunc> funcList = funcListService.queryFuncList();
		Assert.notEmpty(funcList,"加载目录结构出错!请检查重启...");
		Map<Long, SysAuthFunc> funcMap = CoreConstants.funcMap;
		for (SysAuthFunc func:funcList) {
			if(func.getParentId() == 0){
				CoreConstants.allMenu.add(func);
			}else{
				if(func.getIsMenu() == 1){
					SysAuthFunc pFunc = funcMap.get(func.getParentId());
					func.setIsChild(true);
					pFunc.addChild(func);
				}
			}
			funcMap.put(func.getFuncId(),func);
		}
//		Map<Long,SysAuthFunc> funcMap =(Map<Long,SysAuthFunc>) CacheUtils.get(CoreConstants.CACHE_FUNC_MAP);
//		if(funcMap == null){
//			funcMap = new HashMap<Long, SysAuthFunc>();
//		}else{
//			funcMap.clear();
//			CacheUtils.remove(CoreConstants.CACHE_FUNC_MAP);
//		}
//		for (SysAuthFunc func:funcList) {
//			if(func.getParentId() == 0){
//				CoreConstants.allMenu.add(func);
//			}else{
//				if(func.getIsMenu() == 1){
//					SysAuthFunc pFunc = funcMap.get(func.getParentId());
//					func.setIsChild(true);
//					pFunc.addChild(func);
//				}
//			}
//		}
	}
}
