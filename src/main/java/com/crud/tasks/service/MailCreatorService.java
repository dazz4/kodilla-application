package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> funcionality = new ArrayList<>();
        funcionality.add("You can manage your tasks");
        funcionality.add("Provides connection with Trello Account");
        funcionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("button", "Visit website");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_funcionality", funcionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String howManyTasksEmail(String message) {
        List<Task> tasks = taskRepository.findAll();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_friend", false);
        context.setVariable("tasks_json", "http://localhost:8080/v1/task/getTasks");
        context.setVariable("button", "View in JSON");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("tasks", tasks);

        return templateEngine.process("mail/how-many-tasks-mail", context);
    }
}
