package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") int id, @RequestBody Meal meal) {
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDateTime", required = false)
            @DateTimeFormat(iso = ISO.DATE_TIME)
                    LocalDateTime startDateTime,
            @RequestParam(value = "endDateTime", required = false)
            @DateTimeFormat(iso = ISO.DATE_TIME)
                    LocalDateTime endDateTime) {
        LocalDate startDate = (startDateTime == null) ? null : startDateTime.toLocalDate();
        LocalTime startTime = (startDateTime == null) ? null : startDateTime.toLocalTime();
        LocalDate endDate = (endDateTime == null) ? null : endDateTime.toLocalDate();
        LocalTime endTime = (endDateTime == null) ? null : endDateTime.toLocalTime();
        return (startDate == null && startTime == null && endDate == null && endTime == null)
                ? super.getAll()
                : super.getBetween(startDate, startTime, endDate, endTime);
    }

    @GetMapping(value = "/customFormatter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetweenCustomFormatter(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}