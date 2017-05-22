/**
 *The AdminController class has getting and posting requests for page , block ,question Configurations
 * and rules added to questions.
 *
 * @author  CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.controllers;

import com.advice.commons.constants.ModelAttributesConst;
import com.advice.commons.constants.ParameterConst;
import com.advice.commons.constants.RequestMappings;
import com.advice.commons.constants.ReturnConst;
import com.advice.commons.exceptions.ApplicationException;
import com.advice.commons.exceptions.ConnectivityException;
import com.advice.commons.exceptions.InvalidSessionException;
import com.advice.dos.CloudAvailableDO;
import com.advice.dos.OptionsDO;
import com.advice.forms.AdminQuestionForm;
import com.advice.forms.RulesEngineForm;
import com.advice.services.AdminService;
import com.advice.forms.PageForm;
import com.advice.forms.BlockForm;
import com.advice.forms.RuleForm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @InitBinder Annotation that identifies methods which initialize the
 * WebDataBinder which will be used for populating command and form object
 * arguments of annotated handler methods.
 * @ModelAttribute Annotation that binds a method parameter or method return
 * value to a named model attribute, exposed to a web view. Supported for
 * controller classes with '@RequestMapping methods '.
 * @RequestMapping Annotation for mapping web requests onto specific handler
 * classes and/or handler methods. Provides a consistent style between Servlet
 * and Portlet environments.
 * @SessionAttributes Annotation that indicates the session attributes that a
 * specific handler uses. This will typically list the names of model
 * attributes. which should be transparently stored in the session or some
 * conversational storage, serving as form-backing beans.
 * @Autowired Marks a constructor, field, setter method or config method as to
 * be autowired by Spring's dependency injection facilities.
 * @author cjp
 */
@Controller
public class AdminController extends CommonController {

    private static final Logger adminLogger = Logger.getLogger(AdminController.class);
    //it Inject to AdminService. 
    @Autowired
    AdminService adminService;

    String previousQuestionId = null;

    /*
    *@RequestMapping receive the request from AdminPage
    *@Return response to AdminPage jsp page  
     */
    /**
     *
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(RequestMappings.ADMIN)
    public String adminDetails(Model model, HttpServletRequest request, HttpSession session) {
        adminLogger.info("Going to run AdminController setQuestionnaire method");
        try {
            validateSession(request);
        } catch (InvalidSessionException | HttpSessionRequiredException e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController End of setQuestionnaire method");
        return ReturnConst.ADMINPAGE;
    }

    /*
    *@RequestMapping receive the request from AdminPage jsp page
    *@Return response to QuestionsForm jsp page  
     */
    /**
     *
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.POSTQUESTIONS, method = RequestMethod.GET)
    public String listQuestions(Model model, HttpServletRequest request, HttpSession session) {
        //sending the attribute values to jsp page  
        adminLogger.info("Going to run AdminController Questions method");
        try {
            String questionId = request.getParameter("questionId");
            validateSession(request);

            // AdminQuestionForm questionsForm = adminService.listQuestions();
            List<AdminQuestionForm> questionsList = adminService.retrieveQuestionss();

            // List<AdminQuestionForm> adminQuestionForm = adminService.retrieveQuestions();
            Map<Integer, Integer> questionMap = new HashMap<>();

            Map<OptionsDO[], OptionsDO[]> optionsMap = new HashMap<>();
            for (AdminQuestionForm questionForm : questionsList) {
                //System.out.println("questionForm.getQuestionId() : "+questionForm.getQuestionId());
                questionMap.put(questionForm.getQuestionId(), questionForm.getQuestionId());
            }
            for (AdminQuestionForm questionForm : questionsList) {
                //System.out.println("questionForm.getOptions() : " + questionForm.getOptions());
                optionsMap.put(questionForm.getOptions(), questionForm.getOptions());
            }
            int lastQuestionId = adminService.lastQuestionId();

            // model.addAttribute("adminQuestionRetrievalForm", new AdminQuestionRetrievalForm());
            model.addAttribute(ModelAttributesConst.QUESTIONLISTS, questionsList);

            model.addAttribute(ModelAttributesConst.QUESTIONLIST, questionMap);
            model.addAttribute(ModelAttributesConst.LASTQUESTIONID, lastQuestionId);
            model.addAttribute(ModelAttributesConst.OPTIONSLIST, optionsMap);
            model.addAttribute("adminQuestionForm", new AdminQuestionForm());

            //  model.addAttribute(ModelAttributesConst.QUESTIONINPUT, questionsForm);
            adminLogger.info(" AdminController End of Questions method");
        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }

        return ReturnConst.QUESTIONPAGE;
    }

    @RequestMapping(value = RequestMappings.LISTOPTIONS, method = RequestMethod.GET)
    public String listOptions(Model model, HttpServletRequest request, HttpSession session) {
        //sending the attribute values to jsp page  
        adminLogger.info("Going to run AdminController Questions method");
        try {
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            //System.out.println("&&&&&&&&&&&  " + questionId);
            validateSession(request);

            List<AdminQuestionForm> getoptionsList = adminService.retrieveOptions(questionId);

            model.addAttribute(ModelAttributesConst.GETOPTIONSLIST, getoptionsList);

            model.addAttribute("adminQuestionForm", new AdminQuestionForm());

            //  model.addAttribute(ModelAttributesConst.QUESTIONINPUT, questionsForm);
            adminLogger.info(" AdminController End of Questions method");
        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        String redirectUrl = "#model";
        return "redirect:" + redirectUrl;
    }

    /*
    *@RequestMapping receives the request from jsp form
    *@Validated validate AdminQuestionForm 
    *@Return response to QuestionPost jsp page      
     */
    /**
     *
     * @param request
     * @param adminQuestionForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.SAVEQUESTIONS, method = RequestMethod.POST)
    public String postQuestions(HttpServletRequest request, @ModelAttribute("adminQuestionForm") @Validated AdminQuestionForm adminQuestionForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController PostQuestions method");

        try {
            validateSession(request);

            //recieving the data from jsp page and send to adminService page
            adminService.postQuestions(adminQuestionForm);
            adminLogger.info(" AdminController  End of the PostQuestions method");

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        return ReturnConst.REDIRECTQUESTIONPAGE;
    }

    /**
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.DELETEQUESTION, method = RequestMethod.POST)
    public String deleteQuestions(HttpServletRequest request, HttpSession session) {
        adminLogger.info("Going to run AdminController deleteQuestions method");
        try {
            validateSession(request);

            String questionId = request.getParameter(ParameterConst.QUESTIONID);
            adminService.removeQuestion(Integer.parseInt(questionId));

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }

        adminLogger.info("AdminController End of deleteQuestions method");

        return ReturnConst.REDIRECTQUESTIONPAGE;
    }

    /**
     *
     * @param request
     * @param questionForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.UPDATEQUESTION, method = RequestMethod.POST)
    public String updateQuestions(HttpServletRequest request, @ModelAttribute("questionInput") @Validated AdminQuestionForm questionForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController updateQuestions method");
        try {
            validateSession(request);

            adminService.updatedQuestions(questionForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController End ofupdateQuestions method");
        return ReturnConst.REDIRECTQUESTIONPAGE;
    }

    /**
     *
     * @param request
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.GETPAGE, method = RequestMethod.GET)
    public String listPages(HttpServletRequest request, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController getPageSet method");
        try {
            validateSession(request);

            previousQuestionId = null;

            List<PageForm> pagesList = adminService.listPages();
            Map<Integer, String> blockMap = adminService.findAllBlocks();
            int lastPageId = adminService.lastPageId();

            //sending the attribute values to jsp page
            model.addAttribute(ModelAttributesConst.PAGEFORM, new PageForm());
            model.addAttribute(ModelAttributesConst.PAGESLIST, pagesList);
            model.addAttribute(ModelAttributesConst.BLOCKMAP, blockMap);
            model.addAttribute(ModelAttributesConst.LASTPAGEID, lastPageId);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController End of  getPageSet method");
        return ReturnConst.PAGEFORM;

    }

    /**
     *
     * @param request
     * @param pageForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.SAVEPAGE, method = RequestMethod.POST)
    public String postPages(HttpServletRequest request, @ModelAttribute("pageForm") @Validated PageForm pageForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController PostPages method");
        try {
            validateSession(request);

            //recieving the data from jsp page and send to adminService page
            adminService.savePages(pageForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController End of  PostPages method");

        return ReturnConst.REDIRECTPAGE;
    }

    /**
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.DELETEPAGE, method = RequestMethod.POST)
    public String deletePages(HttpServletRequest request, HttpSession session) {
        adminLogger.info("Going to run AdminController deletePages method");
        try {
            validateSession(request);

            // get parameter id using request method and intializing to pageId
            int pageId = Integer.parseInt(request.getParameter(ParameterConst.PAGEID));
            //passing value to admin service removePage method.for removing page from database.
            adminService.removePage(pageId);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController End of deletePages method");
        return ReturnConst.REDIRECTPAGE;
    }

    /**
     *
     * @param request
     * @param pageForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.UPDATEPAGE, method = RequestMethod.POST)
    public String updatePages(HttpServletRequest request, @ModelAttribute("pageForm") @Validated PageForm pageForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController updatePages method");
        try {
            validateSession(request);

            //page pass form to adminService updatePages for updating pages.
            adminService.updatedPages(pageForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController  end of updatePages method");
        return ReturnConst.REDIRECTPAGE;
    }

    /**
     *
     * @param request
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.GETBLOCK, method = RequestMethod.GET)
    public String listBlocks(HttpServletRequest request, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController getBlockset method");
        try {
            validateSession(request);

            // creating new blockForm object    
            //retrieving blockList from adminservice retrieveBlocks method.
            List<BlockForm> blockFormList = adminService.listBlocks();
            List<AdminQuestionForm> adminQuestionForm = adminService.retrieveQuestionss();
            Map<Integer, Integer> questionMap = new HashMap<>();
            for (AdminQuestionForm questionForm : adminQuestionForm) {
                // System.out.println("questionForm.getQuestionId() : " + questionForm.getQuestionId());
                questionMap.put(questionForm.getQuestionId(), questionForm.getQuestionId());

            }
            int lastBlockId = adminService.lastBlockId();

            //sending the attribute values to jsp page
            model.addAttribute(ModelAttributesConst.BLOCKFORM, new BlockForm());
            model.addAttribute(ModelAttributesConst.BLOCKSLIST, blockFormList);
            model.addAttribute(ModelAttributesConst.QUESTIONLIST, questionMap);
            model.addAttribute(ModelAttributesConst.LASTBLOCKID, lastBlockId);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController End of getBlockset method");
        return ReturnConst.BLOCKPAGE;

    }

    /**
     *
     * @param request
     * @param blockForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.SAVEBLOCK, method = RequestMethod.POST)
    public String postBlocks(HttpServletRequest request, @ModelAttribute("blockForm") @Validated BlockForm blockForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController PostBlocks method");
        try {
            validateSession(request);

            //recieving the data from jsp page and send to adminService page
            adminService.saveBlocks(blockForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController End of PostBlocks method");
        return ReturnConst.REDIRECTBLOCKPAGE;
    }

    /**
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.DELETEBLOCK, method = RequestMethod.POST)
    public String deleteBlocks(HttpServletRequest request, HttpSession session) {
        adminLogger.info("Going to run AdminController deleteBlocks method");
        try {
            validateSession(request);
            // get id from parameter using request method and assign to blockId variable
            String blockId = request.getParameter(ParameterConst.BLOCKID);
            //removing block using blockId and pass variable to adminservice removeBlock method.
            adminService.removeBlock(Integer.parseInt(blockId));

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController End of PostBlocks method");
        return ReturnConst.REDIRECTBLOCKPAGE;
    }

    /**
     *
     * @param request
     * @param blockForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.UPDATEBLOCK, method = RequestMethod.POST)
    public String updateBlocks(HttpServletRequest request, @ModelAttribute("blockForm") @Validated BlockForm blockForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController updateBlocks method");
        try {
            validateSession(request);

            //passing blockForm to adminService update method       
            adminService.updatedBlocks(blockForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController End of  PostBlocks method");
        return ReturnConst.REDIRECTBLOCKPAGE;
    }

    /**
     *
     * @param request
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(RequestMappings.GETRULE)
    public String listRules(HttpServletRequest request, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController getRules method");
        try {
            validateSession(request);

            previousQuestionId = null;

            //recieve data from the adminService
          List<RulesEngineForm> rulesEngineFormList = adminService.listRules();
            List<AdminQuestionForm> questionList = adminService.retrieveQuestions();

           

            Map<String, String> questionMap = new HashMap<>();
            Map<Integer, Integer> questionIdMap = new HashMap<>();

            for (AdminQuestionForm questionForm : questionList) {
               
                //to get questions list from questionnaire collection
                questionIdMap.put(questionForm.getQuestionId(), questionForm.getQuestionId());
                questionMap.put(questionForm.getQuestion(), questionForm.getQuestion());
           }

            List<CloudAvailableDO> availbleClouds = adminService.getAvailableRules();

           model.addAttribute(ModelAttributesConst.RULESENGINEFORMLIST, rulesEngineFormList);
            model.addAttribute(ModelAttributesConst.RULESENGINEFORM, new RulesEngineForm());
            model.addAttribute(ModelAttributesConst.QUESTIONLISTS, questionList);
            model.addAttribute(ModelAttributesConst.QUESTIONIDMAP, questionIdMap);
            model.addAttribute(ModelAttributesConst.QUESTIONMAP, questionMap);
            model.addAttribute(ModelAttributesConst.AVAILABLECLOUDS, availbleClouds);
            model.addAttribute(ModelAttributesConst.RULEFORM,new RuleForm());

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController End of getRules method");
        return ReturnConst.RULESPAGE;

    }

    /**
     *
     * @param request
     * @param rulesEngineForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.SAVERULE, method = RequestMethod.POST)
    public String postRules(HttpServletRequest request, @ModelAttribute("rulesEngineForm") @Validated RulesEngineForm rulesEngineForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController PostRules method");
        try {
            validateSession(request);

            //recieving the data from jsp page and send to adminService page for saving rules.
            adminService.postRules(rulesEngineForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController End of getRules method");
        return ReturnConst.REDIRECTRULESPAGE;
    }

    /**
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.DELETERULE, method = RequestMethod.POST)
    public String deleteRules(HttpServletRequest request, HttpSession session) {
        adminLogger.info("Going to run AdminController deleteRules method");
        try {
            validateSession(request);

            //Initializing parameter value as questionId
            String questionId = request.getParameter(ParameterConst.QUESTIONID);
            String choice = request.getParameter(ParameterConst.CHOICE);
            //passing questionId to adminservice removeRules method for removing rule based on questionId
            adminService.removeRules(Integer.parseInt(questionId),choice);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info(" AdminController End of getRules method");
        return ReturnConst.REDIRECTRULESPAGE;
    }

    /**
     *
     * @param request
     * @param rulesEngineForm
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = RequestMappings.UPDATERULE, method = RequestMethod.POST)
    public String updateRules(HttpServletRequest request, @ModelAttribute("rulesEngineForm") @Validated RulesEngineForm rulesEngineForm,
            BindingResult result, Model model, HttpSession session) {
        adminLogger.info("Going to run AdminController updateRules method");
        try {
            int qid = Integer.parseInt(request.getParameter("questionId"));
            validateSession(request);
            System.out.println("Choice Name is"+rulesEngineForm.getQuestionId());
            // passing rulesEngineForm object to adminservice updateRules method for updating rules.
            adminService.updatedRules(rulesEngineForm);

        } catch (InvalidSessionException ise) {
            adminLogger.error("This is Error message", ise);
            return ReturnConst.WELCOME;
        } catch (ConnectivityException ce) {
            adminLogger.error("This is Error message", ce);
            return ReturnConst.WELCOME;
        } catch (ApplicationException ae) {
            adminLogger.error("This is Error message", ae);
            return ReturnConst.WELCOME;
        } catch (Exception e) {
            adminLogger.error("This is Error message", e);
            return ReturnConst.WELCOME;
        }
        adminLogger.info("AdminController  End of updateRules method");
        return ReturnConst.REDIRECTRULESPAGE;
    }

}
