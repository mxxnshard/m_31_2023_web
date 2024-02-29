package web.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.InsectService;
import web.service.dto.InsectDto;
import web.web.form.InsectsForm;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("web/insects")
@AllArgsConstructor
public class InsectWebController extends AbstractController {
    private final InsectService insectService;

    @GetMapping
    public String insectsGet(Model model) {
        Optional<String> userId = getUserId();
        if (userId.isEmpty()) {
            return "redirect:/web/login";
        }
        List<InsectDto> usersInsects = insectService.getUsersInsects(userId.get());

        List<InsectsForm> insectsForms = usersInsects.stream()
                .map(h -> new InsectsForm(h.getName(), h.getSize()))
                .toList();

        model.addAttribute("insects", insectsForms);
        return "insects";
    }

}
