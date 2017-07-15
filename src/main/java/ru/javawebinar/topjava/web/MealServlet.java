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


/**
 * Created by shuto on 15.07.2017.
 */
public class MealServlet extends HttpServlet {
    private static final String DELETE_URL = "/deleteMeal";
    private static final String EDIT_URL = "/editMeal";
    private static final String ADD_URL = "/addMeal";
    private static final String PARAM_ID = "id";
    private static final String PARAM_DATETIME = "dateTime";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_CALORIES = "calories";

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private CrudService crudService = new CrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("dispatch to meals");
        request.setAttribute("meals", crudService.getMeals(LocalTime.MIN, LocalTime.MAX));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("post request: " + request.getServletPath());

        request.setCharacterEncoding("UTF-8");

        switch (request.getServletPath()) {
            case DELETE_URL:
                crudService.deleteMeal(Integer.parseInt(request.getParameter(PARAM_ID)));
                break;
            case EDIT_URL:
                crudService.editMeal(Integer.parseInt(request.getParameter(PARAM_ID)),
                        LocalDateTime.parse(request.getParameter(PARAM_DATETIME)),
                        request.getParameter(PARAM_DESCRIPTION),
                        Integer.parseInt(request.getParameter(PARAM_CALORIES)));
                break;
            case ADD_URL:
                crudService.addMeal(LocalDateTime.parse(request.getParameter(PARAM_DATETIME)),
                        request.getParameter(PARAM_DESCRIPTION),
                        Integer.parseInt(request.getParameter(PARAM_CALORIES)));
                break;
        }

        response.sendRedirect("meals");
    }
}
