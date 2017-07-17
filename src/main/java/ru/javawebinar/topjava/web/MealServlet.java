package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.service.CrudService;
import ru.javawebinar.topjava.service.CrudServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {
    private static final String DELETE_STR = "delete";
    private static final String EDIT_STR = "edit";
    private static final String ADD_STR = "add";
    private static final String PARAM_ID = "id";
    private static final String PARAM_DATETIME = "dateTime";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_CALORIES = "calories";

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private CrudService crudService;

    @Override
    public void init() throws ServletException {
        super.init();
        crudService = new CrudServiceImpl();
        crudService.initTestData();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("dispatch to meals");
        String action = request.getParameter("action");
        if (action != null && action.equals(EDIT_STR)) {
            request.setAttribute("meal", crudService.getMeal(Integer.parseInt(request.getParameter(PARAM_ID))));
            request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
        } else {
            request.setAttribute("meals", crudService.getMeals());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case DELETE_STR:
                crudService.deleteMeal(Integer.parseInt(request.getParameter(PARAM_ID)));
                break;
            case EDIT_STR:
                crudService.editMeal(Integer.parseInt(request.getParameter(PARAM_ID)),
                        LocalDateTime.parse(request.getParameter(PARAM_DATETIME)),
                        request.getParameter(PARAM_DESCRIPTION),
                        Integer.parseInt(request.getParameter(PARAM_CALORIES)));
                break;
            case ADD_STR:
                crudService.addMeal(LocalDateTime.parse(request.getParameter(PARAM_DATETIME)),
                        request.getParameter(PARAM_DESCRIPTION),
                        Integer.parseInt(request.getParameter(PARAM_CALORIES)));
                break;
        }

        response.sendRedirect("meals");
    }
}
