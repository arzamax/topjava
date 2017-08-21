package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/meals")
public class MealController extends MealRestController {

    public MealController(MealService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String meals(@RequestParam(value = "startDate", required = false) String startDateStr,
                        @RequestParam(value = "startTime", required = false) String startTimeStr,
                        @RequestParam(value = "endDate", required = false) String endDateStr,
                        @RequestParam(value = "endTime", required = false) String endTimeStr, Model model) {
        if (startDateStr == null && endDateStr == null && startTimeStr == null && endTimeStr == null) {
            model.addAttribute("meals", super.getAll());
        } else {
            model.addAttribute("meals", super.getBetween(
                    StringUtils.isEmpty(startDateStr) ? null : LocalDate.parse(startDateStr),
                    StringUtils.isEmpty(startTimeStr) ? null : LocalTime.parse(startTimeStr),
                    StringUtils.isEmpty(endDateStr) ? null : LocalDate.parse(endDateStr),
                    StringUtils.isEmpty(endTimeStr) ? null : LocalTime.parse(endTimeStr)));
        }
        return "meals";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String mealForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("meal", super.get(id));
        return "mealForm";
    }

    @RequestMapping(value = "/creating", method = RequestMethod.GET)
    public String mealForm(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveMeal(@RequestParam("id") Integer id,
                           @RequestParam("dateTime") String dateTime,
                           @RequestParam("description") String description,
                           @RequestParam("calories") int calories) {
        Meal meal = new Meal(id, LocalDateTime.parse(dateTime), description, calories);
        if (id == null) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
        return "redirect:/meals";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteMeal(@PathVariable("id") int id) {
        super.delete(id);
        return "redirect:/meals";
    }
}
