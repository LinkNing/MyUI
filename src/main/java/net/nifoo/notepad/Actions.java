package net.nifoo.notepad;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.Action;
import javax.swing.ActionMap;

import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ProxyActions;

@ProxyActions({ "open", "close", //
		"undo", "redo", "bold", "italic", "underline", // 
		"upperCase", "lowerCase" //
})
public class Actions {

	private Map<String, Object> actionHosts;

	private ApplicationContext applicationContent;

	public Actions(ApplicationContext applicationContent) {
		this.applicationContent = applicationContent;
		this.actionHosts = new ConcurrentHashMap<>();
	}

	private ApplicationContext getContext() {
		return applicationContent;
	}

	public Object putCategory(String category, Object actionHost) {
		Object oldHost = actionHosts.put(category, actionHost);
		return oldHost;
	}

	public Set<String> getCategories() {
		return actionHosts.keySet();
	}

	public ActionMap getCategory(String category) {
		Object cat = actionHosts.get(category);
		return getContext().getActionMap(cat);
	}

	public ActionMap getActionMap(Object obj) {
		return getContext().getActionMap(obj);
	}

	public ActionMap getActionMap(Class<?> clazz, Object obj) {
		return getContext().getActionMap(clazz, obj);
	}

	public javax.swing.Action getAction(String categoryName, String actionName) {
		Object cat = actionHosts.get(categoryName);
		return getContext().getActionMap(cat).get(actionName);
	}

	public javax.swing.Action getAction(Object obj, String actionName) {
		return getContext().getActionMap(obj).get(actionName);
	}

	public javax.swing.Action getAction(Class<?> clazz, Object obj, String actionName) {
		return getContext().getActionMap(clazz, obj).get(actionName);
	}

	//	public javax.swing.Action getAction(String actionName) {
	//		ApplicationContext ac = getContext();
	//
	//		Action action = ac.getActionMap().get(actionName);
	//
	//		if (action == null) {
	//
	//			for (Object obj : actionHosts.values()) {
	//				action = ac.getActionMap(obj).get(actionName);
	//				if (action != null) {
	//					break;
	//				}
	//			}
	//		}
	//
	//		return action;
	//	}

	public javax.swing.Action getAction(String actionName) {
		ApplicationContext ac = getContext();

		Action action = ac.getActionMap().get(actionName);

		if (action == null) {
			action = ac.getActionMap(this).get(actionName);
		}

		return action;
	}

	public javax.swing.Action getGlobleAction(String actionName) {
		return getContext().getActionMap().get(actionName);
	}
}
