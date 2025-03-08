package es.insinno.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "An unexpected error has occurred";
        String errorPage = "error/general";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
                case 403 -> {
                    errorMessage = "Access Denied";
                    errorPage = "error/403";
                }
                case 404 -> {
                    errorMessage = "Page Not Found";
                    errorPage = "error/404";
                }
                case 500 -> {
                    errorMessage = "Internal Server Error";
                    errorPage = "error/500";
                }
            }
            model.addAttribute("status", statusCode);
        }

        model.addAttribute("message", errorMessage);
        return errorPage;
    }
}