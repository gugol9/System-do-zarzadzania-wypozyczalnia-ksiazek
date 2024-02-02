package com.KamilMarkowski.library.Error;

import com.KamilMarkowski.library.LibraryApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Obsługa błędów ogólnych (dowolny błąd)
        return "error/error";
    }

    @RequestMapping("/error/403")
    public String handle403Error() {
        // Obsługa błędów
        return "error/403";
    }
}
