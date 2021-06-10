package com.codesquad.issuetracker.controller;

import com.codesquad.issuetracker.ApiResponse;
import com.codesquad.issuetracker.service.GitHubLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class GitHubLoginController {

    private Logger logger = LoggerFactory.getLogger(GitHubLoginController.class);

    private final GitHubLoginService loginService;

    public GitHubLoginController(GitHubLoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/github")
    public ApiResponse githubLogin(@RequestParam String code) {
        logger.debug("code : {} ", code);

        return ApiResponse.ok(loginService.login(code));
    }

}
