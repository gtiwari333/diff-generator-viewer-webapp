package diff;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@Controller
class DiffController {

    @GetMapping({"/", ""})
    String getHome() {
        return "redirect:index.html";
    }

    @PostMapping(value = "/diff")
    ResponseEntity<String> doDiff(@RequestBody DiffRequest req) throws Exception {
        return ok(getDiffStr(req.left, req.right, "Diff"));
    }

    static class DiffRequest {
        public String left;
        public String right;
    }

    String getDiffStr(String left, String right, String label) throws Exception {
        //left is the reference
        var originalLines = Arrays.stream(left.split("\n")).collect(toList());

        var patch = DiffUtils.diff(left, right, null);

        var diffStrList = UnifiedDiffUtils.generateUnifiedDiff(label, label, originalLines, patch, 10);

        return String.join("\n", diffStrList);
    }

}
