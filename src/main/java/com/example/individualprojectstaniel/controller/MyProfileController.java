    package com.example.individualprojectstaniel.controller;

    import com.example.individualprojectstaniel.model.dto.MyProfileDTO;
    import com.example.individualprojectstaniel.model.entity.UserEntity;
    import com.example.individualprojectstaniel.service.UserService;
    import org.springframework.security.core.Authentication;
    import org.springframework.stereotype.Controller;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.servlet.ModelAndView;

    import java.util.List;

    @Controller
    @RequestMapping("/myprofile")
    public class MyProfileController {
        private final UserService userService;

        public MyProfileController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping()
        public ModelAndView myProfileHome(Authentication auth) {
            ModelAndView modelAndView = new ModelAndView("myprofile");
            UserEntity user = userService.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalStateException("Username not found!" + auth.getName()));

            MyProfileDTO myProfileDTO = new MyProfileDTO(user);
            modelAndView.addObject("profile", myProfileDTO);

            return modelAndView;
        }

        @GetMapping("/edit/{id}")
        public ModelAndView showEditProfileForm(@PathVariable Long id) {
            MyProfileDTO myProfileDTO = userService.getUserById(id);

            ModelAndView modelAndView = new ModelAndView();

            if (myProfileDTO != null) {
                modelAndView.setViewName("editprofile");
                modelAndView.addObject("myProfileDTO", myProfileDTO);
                modelAndView.addObject("genders", List.of("MALE", "FEMALE"));
            } else {
                modelAndView.setViewName("error-page");
            }

            return modelAndView;
        }

        @PostMapping("/edit/{id}")
        public ModelAndView editMyProfile(MyProfileDTO myProfileDTO, @PathVariable Long id) {
            userService.editUser(id, myProfileDTO);
            return new ModelAndView("redirect:/myprofile");
        }



        @GetMapping("/error")
        public ModelAndView error(BindingResult result) {
            return new ModelAndView("myprofile");
        }
    }
