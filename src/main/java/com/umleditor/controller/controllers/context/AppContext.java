package com.umleditor.controller.controllers.context;

import com.umleditor.controller.controllers.pagecontrol.annotations.PageController;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.common.interfaces.UMLDiagram;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static Map<AppPage, Object> pageControllers = new HashMap<>();
    private static Map<Class<? extends UMLDiagram>,AppPage> nameMap = new HashMap<>();

    public static void addPageController(AppPage pageName, Object pageController) {
        if (pageController.getClass().isAnnotationPresent(PageController.class)) {
            pageControllers.put(pageName, pageController);
        } else {
            throw new IllegalArgumentException("Object that was given has no PageController annotation");
        }
    }
    public static void removePageController(AppPage pageName) {
        pageControllers.remove(pageName);
    }
    public static Object getPageController(AppPage pageName) {
        return pageControllers.get(pageName);
    }

    public static void mapPageToDiagram(AppPage pageName,
                                        Class<? extends UMLDiagram> diagram) {
        nameMap.put(diagram,pageName);
    }
    public static void unmapPageFromDiagram(Class<? extends UMLDiagram> diagram) {
        nameMap.remove(diagram);
    }
    public static AppPage getPageByDiagram(Class<? extends UMLDiagram> diagram) {
        return nameMap.get(diagram);
    }
}
