package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangeShortcutsController {
    @Autowired
    private ShortcutRepository shortcutRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/changeShortcuts")
    public String changeShortcuts(@RequestParam(defaultValue = "false") Boolean notesShortcut, @RequestParam(defaultValue = "false") Boolean profileShortcut, HttpServletRequest request) {
//        Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        String email = request.getSession().getAttribute("user").toString();
        User user = userRepository.findByEmail(email);

        Shortcut userShortcut = shortcutRepository.findByUserId(user.id);
        userShortcut.profileShortcut = profileShortcut;
        userShortcut.notesShortcut = notesShortcut;
        shortcutRepository.save(userShortcut);
        return "redirect:/shortcuts";
    }
}
