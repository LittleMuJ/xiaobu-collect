/*package com.mrs.demo.common.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;



*//**
 * 菜单工具类
 * 
 * @author Mmmmm
 *
 *//*
public class MenuUtil {

	
	public static String getMenu(List<SysMenu> menuList,List<String> userMenus){
		
		StringBuilder sb = new StringBuilder();
		
		if(menuList != null && menuList.size() > 0){
			
			for(SysMenu menu : menuList){
				
				if(userMenus.contains(menu.getId())){
					int childNum = menu.getChildren().size();
					
					sb.append("<li class=\"treeview\">\n")
					.append("<a href=\"#\" ");
					if(StringUtils.isNotEmpty(menu.getMenuUrl()) && childNum == 0) {
						sb.append("onclick=\"menuToPage(this,'"+menu.getMenuUrl()+"')\"");
					}
					sb.append(">\n");
					if(StringUtils.isNotEmpty(menu.getMenuIcon())){
						
						sb.append("<i class=\""+menu.getMenuIcon()+"\"></i>\n");
					} else {
						
						sb.append("<i class=\"fa fa-circle-o\"></i>\n");
					}
					sb.append("<span>"+menu.getMenuName()+"</span>\n");
					if(childNum > 0){
						sb.append("<span class=\"pull-right-container\">\n")
						  .append("<i class=\"fa fa-angle-left pull-right\"></i>")
						  .append("</span>");
					}
					sb.append("</a>\n");
					if(childNum > 0){
						sb.append(getChildren(menu.getChildren(),userMenus));
					}
					sb.append("</li>\n");
				}
			}
		}
		
		return sb.toString();
	}
	
	public static String getChildren(List<SysMenu> menuList,List<String> userMenus){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ul class=\"treeview-menu\">\n");
		
		for(SysMenu menu : menuList){
			
			if(userMenus.contains(menu.getId())){
				int childNum = menu.getChildren().size();
				
				sb.append("<li>\n")
				.append("<a href=\"#\" ");
				if(StringUtils.isNotEmpty(menu.getMenuUrl()) && childNum == 0){
					sb.append("onclick=\"menuToPage(this,'"+menu.getMenuUrl()+"')\"");
				}
				sb.append(">\n");
				if(StringUtils.isNotEmpty(menu.getMenuIcon())){
					
					sb.append("<i class=\""+menu.getMenuIcon()+"\"></i>\n");
				} else {
					
					sb.append("<i class=\"fa fa-circle-o\"></i>\n");
				}
				sb.append("<span>"+menu.getMenuName()+"</span>\n");
				if(childNum > 0){
					sb.append("<span class=\"pull-right-container\">\n")
					  .append("<i class=\"fa fa-angle-left pull-right\"></i>")
					  .append("</span>");
				}
				sb.append("</a>\n");
				if(childNum > 0){
					sb.append(getChildren(menu.getChildren(),userMenus));
				}
				sb.append("</li>\n");
			}
		}
		
		sb.append("</ul>");
		
		return sb.toString();
	}
}
*/