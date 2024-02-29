package web.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import web.service.InsectService;
import web.service.dto.InsectDto;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/insect")
public class InsectController {
    private final InsectService insectService;

    @PostMapping(path = "insert/{name}/{size}")
    public ResponseEntity<String> insertInsect(
            @PathVariable String name,
            @PathVariable Integer size,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("can not add insect");
        }

        InsectDto insectDto = new InsectDto(userId, name, size);
        String id = insectService.insertInsect(insectDto);
        return ResponseEntity.ok(id);
    }

}
