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
import java.time.LocalTime;
import java.util.List;


/**
 * Created by shuto on 15.07.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private CrudService crudService = new CrudServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("dispatch to meals");
        request.setAttribute("meals", crudService.getMeals(LocalTime.MIN, LocalTime.MAX));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
