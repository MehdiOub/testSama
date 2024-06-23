package ma.zs.zyn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.service.facade.admin.collaborator.CollaboratorAdminService;
import ma.zs.zyn.zynerator.security.bean.*;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.service.facade.*;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState;
import ma.zs.zyn.service.facade.admin.collaborator.PaimentCollaboratorStateAdminService;
import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.service.facade.admin.project.ProjectStateAdminService;
import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import ma.zs.zyn.service.facade.admin.packaging.CategoryPackagingAdminService;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaboratorState;
import ma.zs.zyn.service.facade.admin.collaborator.InscriptionCollaboratorStateAdminService;

import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class ZynApplication {
    public static ConfigurableApplicationContext ctx;
    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(ZynApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , CollaboratorAdminService collaboratorService) {
    return (args) -> {
        if(true){

            createPaimentCollaboratorState();
            createProjectState();
            createCategoryPackaging();
            createInscriptionCollaboratorState();

        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Collaborator
        Collaborator userForCollaborator = new Collaborator("collaborator");
		userForCollaborator.setPassword("123");
		// Role Collaborator
		Role roleForCollaborator = new Role();
		roleForCollaborator.setAuthority(AuthoritiesConstants.COLLABORATOR);
		roleForCollaborator.setCreatedAt(LocalDateTime.now());
		Role roleForCollaboratorSaved = roleService.create(roleForCollaborator);
		RoleUser roleUserForCollaborator = new RoleUser();
		roleUserForCollaborator.setRole(roleForCollaboratorSaved);
		if (userForCollaborator.getRoleUsers() == null)
			userForCollaborator.setRoleUsers(new ArrayList<>());

		userForCollaborator.getRoleUsers().add(roleUserForCollaborator);
		if (userForCollaborator.getModelPermissionUsers() == null)
			userForCollaborator.setModelPermissionUsers(new ArrayList<>());


        userForCollaborator.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        collaboratorService.create(userForCollaborator);

            }
        };
    }



    private void createPaimentCollaboratorState(){
            PaimentCollaboratorState itemSuccess = new PaimentCollaboratorState();
            itemSuccess.setCode("success");
            itemSuccess.setLibelle("Validated");
            paimentCollaboratorStateService.create(itemSuccess);
            PaimentCollaboratorState itemDanger = new PaimentCollaboratorState();
            itemDanger.setCode("danger");
            itemDanger.setLibelle("Blocked");
            paimentCollaboratorStateService.create(itemDanger);
            PaimentCollaboratorState itemWarning = new PaimentCollaboratorState();
            itemWarning.setCode("warning");
            itemWarning.setLibelle("Pending");
            paimentCollaboratorStateService.create(itemWarning);

    }
    private void createProjectState(){
            ProjectState itemSuccess = new ProjectState();
            itemSuccess.setCode("success");
            itemSuccess.setLibelle("Validated");
            projectStateService.create(itemSuccess);
            ProjectState itemDanger = new ProjectState();
            itemDanger.setCode("danger");
            itemDanger.setLibelle("Blocked");
            projectStateService.create(itemDanger);
            ProjectState itemWarning = new ProjectState();
            itemWarning.setCode("warning");
            itemWarning.setLibelle("Pending");
            projectStateService.create(itemWarning);

    }
    private void createCategoryPackaging(){
            CategoryPackaging itemWarning = new CategoryPackaging();
            itemWarning.setCode("warning");
            itemWarning.setLibelle("Silver");
            categoryPackagingService.create(itemWarning);
            CategoryPackaging itemDanger = new CategoryPackaging();
            itemDanger.setCode("danger");
            itemDanger.setLibelle("Gold");
            categoryPackagingService.create(itemDanger);
            CategoryPackaging itemSuccess = new CategoryPackaging();
            itemSuccess.setCode("success");
            itemSuccess.setLibelle("Free");
            categoryPackagingService.create(itemSuccess);

    }
    private void createInscriptionCollaboratorState(){
            InscriptionCollaboratorState itemSuccess = new InscriptionCollaboratorState();
            itemSuccess.setCode("success");
            itemSuccess.setLibelle("Validated");
            inscriptionCollaboratorStateService.create(itemSuccess);
            InscriptionCollaboratorState itemDanger = new InscriptionCollaboratorState();
            itemDanger.setCode("danger");
            itemDanger.setLibelle("Blocked");
            inscriptionCollaboratorStateService.create(itemDanger);
            InscriptionCollaboratorState itemWarning = new InscriptionCollaboratorState();
            itemWarning.setCode("warning");
            itemWarning.setLibelle("Pending");
            inscriptionCollaboratorStateService.create(itemWarning);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("Technology"));
        modelPermissions.add(new ModelPermission("Template"));
        modelPermissions.add(new ModelPermission("PaimentCollaborator"));
        modelPermissions.add(new ModelPermission("Project"));
        modelPermissions.add(new ModelPermission("PaimentCollaboratorState"));
        modelPermissions.add(new ModelPermission("ProjectState"));
        modelPermissions.add(new ModelPermission("CategoryPackaging"));
        modelPermissions.add(new ModelPermission("ProjectTemplate"));
        modelPermissions.add(new ModelPermission("Packaging"));
        modelPermissions.add(new ModelPermission("InscriptionCollaboratorState"));
        modelPermissions.add(new ModelPermission("Collaborator"));
        modelPermissions.add(new ModelPermission("InscriptionCollaborator"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    PaimentCollaboratorStateAdminService paimentCollaboratorStateService;
    @Autowired
    ProjectStateAdminService projectStateService;
    @Autowired
    CategoryPackagingAdminService categoryPackagingService;
    @Autowired
    InscriptionCollaboratorStateAdminService inscriptionCollaboratorStateService;
}


