/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.services;

import com.advice.dao.BlockDAO;
import com.advice.dao.CloudAvailableDAO;
import com.advice.dao.PageDAO;
import com.advice.dao.QuestionDAO;
import com.advice.dao.RulesDAO;
import com.advice.dos.PageDO;
import com.advice.dos.BlockDO;
import com.advice.dos.AdminQuestionDO;
import com.advice.dos.CloudAvailableDO;
import com.advice.dos.CloudScore;
import com.advice.dos.OptionsDO;
import com.advice.dos.QuestionDO;
import com.advice.dos.RulesEngineDO;
import com.advice.forms.AdminQuestionForm;
import com.advice.forms.BlockForm;
import com.advice.forms.Choice;
import com.advice.forms.PageForm;
import com.advice.forms.RuleForm;
import com.advice.forms.RulesEngineForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @ @Service This annotation serves as a specialization of @Component, allowing
 * for implementation classes to be autodetected through classpath scanning. In
 * AdminService class
 * @author cjp
 */
@Service
public class AdminService {

    private static final Logger adminServiceLogger = Logger.getLogger(AdminService.class);

    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    RulesDAO rulesDAO;

    @Autowired
    PageDAO pageDAO;

    @Autowired
    BlockDAO blockDAO;

    @Autowired
    CloudAvailableDAO cloudAvailableDAO;

    /**
     *
     * @return adminQuestionForm
     */
    public AdminQuestionForm listQuestions() {
        adminServiceLogger.info("Going to run  AdminService initializeQuestion method");
        AdminQuestionForm adminQuestionForm = null;
        try {
            adminQuestionForm = new AdminQuestionForm();
            // find questionId from adminDAo and set to ques
            adminQuestionForm.setQuestionId(questionDAO.findQuestionId());

        } catch (Exception e) {

        }
        adminServiceLogger.info(" AdminService End of initializeQuestion method");
        return adminQuestionForm;
    }

    /**
     * create AdminQuestionDO object and set AdminQuestionForm variables to
     * AdminQuestionDO Pass adminQuestionDO to addData method in AdminDAO
     *
     * @param adminQuestionForm
     * @throws java.lang.Exception
     */
    public void postQuestions(AdminQuestionForm adminQuestionForm) throws Exception {
        try {

            adminServiceLogger.info("Going to run  AdminService postQuestions method");

            AdminQuestionDO adminQuestionDo = new AdminQuestionDO();

            adminQuestionDo.setQuestion(adminQuestionForm.getQuestion());
            adminQuestionDo.setQuestionId(questionDAO.findQuestionId());
            adminQuestionDo.setUIItem(adminQuestionForm.getUIItem());
            adminQuestionDo.setRuleRequired(adminQuestionForm.getRuleRequired());
            adminQuestionDo.setMandatory(adminQuestionForm.getMandatory());
            adminQuestionDo.setQuestionType(adminQuestionForm.getQuestionType());
            adminQuestionDo.setOptions(adminQuestionForm.getOptions());
            questionDAO.saveQuestion(adminQuestionDo);
        } catch (CloneNotSupportedException ex) {
            adminServiceLogger.error("This is Error message", new Exception("CloneNotSupportedException"));
        } catch (Exception e) {

        }
        adminServiceLogger.info(" AdminService end of postQuestions method");
    }

    /**
     * retrieveQuestions methods is for getting adminQuestionDOList from
     * adminDAo through retrieveData method. Extract adminQuestionDOList and set
     * variables to adminQuestionFormlist and add adminQuestionList
     *
     * @return adminQuestionFormList
     * @throws java.lang.Exception
     */
    public List<AdminQuestionForm> retrieveQuestionsWithOptions() throws Exception {
        adminServiceLogger.info("Going to run  AdminService retrieveQuestions method");
        List<AdminQuestionForm> adminQuestionFormList = null;
        try {

            adminQuestionFormList = new ArrayList<>();
            // 

            List<AdminQuestionDO> adminQuestionDOList = questionDAO.findAllQuestions();

            for (AdminQuestionDO questionnaire : adminQuestionDOList) {
                AdminQuestionForm adminQuestionForm = new AdminQuestionForm();
                adminQuestionForm.setQuestionId(questionnaire.getQuestionId());
                adminQuestionForm.setQuestion(questionnaire.getQuestion());

                adminQuestionForm.setUIItem(questionnaire.getUIItem());
                adminQuestionForm.setMandatory(questionnaire.getMandatory());
                adminQuestionForm.setOptions(questionnaire.getOptions());
                Map<String, String> optionsMap = new HashMap<>();
                for (OptionsDO option : questionnaire.getOptions()) {
                    optionsMap.put(option.getOption(), option.getOption());
                }
                adminQuestionForm.setOptionsMap(optionsMap);
                adminQuestionForm.setQuestionType(questionnaire.getQuestionType());

                adminQuestionFormList.add(adminQuestionForm);

            }

        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of retrieveQuestions method");
        return adminQuestionFormList;
    }

    public List<AdminQuestionForm> retrieveQuestions() throws Exception {
        adminServiceLogger.info("Going to run  AdminService retrieveQuestions method");
        List<AdminQuestionForm> adminQuestionFormList = null;
        try {

            adminQuestionFormList = new ArrayList<>();
            // 

            List<AdminQuestionDO> adminQuestionDOList = questionDAO.findAllQuestions();

            for (AdminQuestionDO questionnaire : adminQuestionDOList) {
                if (!("no".equalsIgnoreCase(questionnaire.getRuleRequired())
                        || "text".equalsIgnoreCase(questionnaire.getUIItem()))) {
                    AdminQuestionForm adminQuestionForm = new AdminQuestionForm();
                    adminQuestionForm.setQuestionId(questionnaire.getQuestionId());
                    adminQuestionForm.setQuestion(questionnaire.getQuestion());
                    adminQuestionForm.setUIItem(questionnaire.getUIItem());
                    adminQuestionForm.setMandatory(questionnaire.getMandatory());
                    adminQuestionForm.setOptions(questionnaire.getOptions());
                    adminQuestionForm.setQuestionType(questionnaire.getQuestionType());
                    adminQuestionForm.setRuleRequired(questionnaire.getRuleRequired());
                    adminQuestionFormList.add(adminQuestionForm);
                }
            }

        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of retrieveQuestions method");
        return adminQuestionFormList;
    }
     public List<AdminQuestionForm> retrieveQuestionss() throws Exception {
        adminServiceLogger.info("Going to run  AdminService retrieveQuestions method");
        List<AdminQuestionForm> adminQuestionFormList = null;
        try {

            adminQuestionFormList = new ArrayList<>();
            // 

            List<AdminQuestionDO> adminQuestionDOList = questionDAO.findAllQuestions();

            for (AdminQuestionDO questionnaire : adminQuestionDOList) {
               
                    AdminQuestionForm adminQuestionForm = new AdminQuestionForm();
                    adminQuestionForm.setQuestionId(questionnaire.getQuestionId());
                    adminQuestionForm.setQuestion(questionnaire.getQuestion());
                    adminQuestionForm.setUIItem(questionnaire.getUIItem());
                    adminQuestionForm.setMandatory(questionnaire.getMandatory());
                    adminQuestionForm.setOptions(questionnaire.getOptions());
                    adminQuestionForm.setQuestionType(questionnaire.getQuestionType());
                    adminQuestionForm.setRuleRequired(questionnaire.getRuleRequired());
                    adminQuestionFormList.add(adminQuestionForm);
               
            }

        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of retrieveQuestions method");
        return adminQuestionFormList;
    }

    /**
     * removePage method get pageId from AdminQuestionController and send to
     * AdminDAO
     *
     * @param questionId
     * @throws java.lang.Exception
     */
    public void removeQuestion(int questionId) throws Exception {
        adminServiceLogger.info("Going to run  AdminService removeQuestion method");
        try {
            questionDAO.deleteQuestion(questionId);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of removeQuestion method");
    }

    /**
     * updatedQuestions method get adminQuestionForm from
     * AdminQuestionController , set adminQuestionForm to adminQuestionDo send
     * to AdminDAO updateQuestion Method.
     *
     * @param adminQuestionForm
     * @throws java.lang.Exception
     */
    public void updatedQuestions(AdminQuestionForm adminQuestionForm) throws Exception {
        adminServiceLogger.info("Going to run  AdminService updatedQuestions method");
        try {

            AdminQuestionDO adminQuestionDo = new AdminQuestionDO();

            adminQuestionDo.setQuestionId(adminQuestionForm.getQuestionId());
            adminQuestionDo.setQuestion(adminQuestionForm.getQuestion());
            adminQuestionDo.setUIItem(adminQuestionForm.getUIItem());
            adminQuestionDo.setMandatory(adminQuestionForm.getMandatory());
            adminQuestionDo.setRuleRequired(adminQuestionForm.getRuleRequired());
            adminQuestionDo.setOptions(adminQuestionForm.getOptions());
            adminQuestionDo.setQuestionType(adminQuestionForm.getQuestionType());
            questionDAO.updateQuestion(adminQuestionDo);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of updatedQuestions method");
    }

    /**
     * get rules data from adminDAO and assign variable as rulesList extract
     * rulesEngineDOList,set RulesEngineDO object variable to RulesEngineForm
     * object variable. Add rulesEngineform to rulesEngineFormList arrayList
     *
     * @return rulesEngineFormList
     * @throws java.lang.Exception
     */
    public List<RulesEngineForm> listRules() throws Exception {
        adminServiceLogger.info("Going to run  AdminService initializeRulesEngine method");
        List<RulesEngineForm> rulesEngineFormList = null;
        try {
            rulesEngineFormList = new ArrayList<>();
            List<AdminQuestionDO> questionList = questionDAO.findAllQuestions();
            List<Integer> questionIdList = new ArrayList<>();
            for (AdminQuestionDO adminQuestionDO : questionList) {
                questionIdList.add(adminQuestionDO.getQuestionId());
            }

            //Map<Integer, String> rulesList = new HashMap<>();
            List<RulesEngineDO> rulesEngineDOList = rulesDAO.findAllrules();

            for (Integer questionId : questionIdList) {
                RulesEngineForm rulesEngineForm = new RulesEngineForm();

                rulesEngineForm.setQuestionId(String.valueOf(questionId));
                List<RuleForm> ruleFormList = new ArrayList<>();
                for (RulesEngineDO rulesEngineDO : rulesEngineDOList) {
                    if (rulesEngineDO.getQuestionId() == questionId) {
                        RuleForm ruleForm = new RuleForm();
                        ruleForm.setQuestionId(String.valueOf(rulesEngineDO.getQuestionId()));
                        ruleForm.setRemarks(rulesEngineDO.getRemarks());
                        ruleForm.setScoring(rulesEngineDO.getScoring());
                        ruleForm.setChoiceName(rulesEngineDO.getChoice());
                        ruleFormList.add(ruleForm);
                    }
                }
                if (!ruleFormList.isEmpty()) {
                    rulesEngineForm.setRuleForm(ruleFormList);

                    rulesEngineFormList.add(rulesEngineForm);
                }
            }
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of initializeRulesEngine method");
        System.out.println("The rulesFormList is" + rulesEngineFormList);
        return rulesEngineFormList;
    }

    /**
     * postRulesEngine for getting rulesEngineForm from AdminController and
     * setting rulesengineDO and passing RulesEngineDO Object to adminDAO Create
     * RulesEngineDO object and set RulesEngineForm objects variables to
     * rulesEngineDO rulesEngineDO pass to AdminDAO class postRulesData Method
     *
     * @param rulesEngineForm
     */
    List<String> QuestionDataList = new ArrayList<>();

    /**
     *
     * @param rulesEngineForm
     * @throws Exception
     */
    public void postRules(RulesEngineForm rulesEngineForm) throws Exception {
        try {

            adminServiceLogger.info("Going to run  AdminService postRulesEngine method");

            for (RuleForm rule : rulesEngineForm.getRuleForm()) {
                RulesEngineDO rulesEngineDO = new RulesEngineDO();
                rulesEngineDO.setChoice(rule.getChoiceName());
                rulesEngineDO.setQuestionId(Integer.parseInt(rulesEngineForm.getQuestionId()));
                rulesEngineDO.setRemarks(rule.getRemarks());
                rulesEngineDO.setScoring(rule.getScoring());
                java.util.Date date = new java.util.Date();
                rulesEngineDO.setCreationDate(date);
                rulesEngineDO.setModificationDate(date);

                rulesDAO.saveRule(rulesEngineDO);

            }

//            rulesEngineDO.setQuestionId(Integer.parseInt(rulesEngineForm.getQuestionId()));
//            for (Choice choice : rulesEngineForm.getChoice()) {
//                rulesEngineDO.setChoice(choice.getChoiceName());
//                rulesEngineDO.setRemarks(choice.getRemarks());
//                rulesEngineDO.setScoring(choice.getScoring());
//                java.util.Date date = new java.util.Date();
//                rulesEngineDO.setCreationDate(date);
//                rulesEngineDO.setModificationDate(date);
//
//                rulesDAO.saveRule(rulesEngineDO);
//
//            }
//            QuestionDataList.add(rulesEngineForm.getQuestions());
//            //rulesEngineDO.setCloud(rulesEngineForm.getCloud());
//            //rulesEngineDO.setScore(rulesEngineForm.getScore());
//            String question = rulesEngineForm.getQuestions();
//            int questionId = Integer.parseInt(question);
//          //  rulesEngineDO.setQuestionId(questionId);
//            //rulesEngineDO.setChoice(rulesEngineForm.getChoice());
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of postRulesEngine method");
    }

    /**
     * savePages is getting setting pageDO object from PagForm object. pass
     * pageDO to PageDAO savePage method.
     *
     * @param pageForm
     * @throws java.lang.Exception
     */
    public void savePages(PageForm pageForm) throws Exception {
        adminServiceLogger.info("Going to run  AdminService savePages method");
        try {

            PageDO pageDO = new PageDO();
            pageDO.setPageId(pageForm.getPageId());
            pageDO.setPageTitle(pageForm.getPageTitle());

            Map<Integer, Integer> blockMap = new HashMap<>();

            for (int blockId : pageForm.getBlockSet()) {
                blockMap.put(blockId, blockId);
            }

            pageDO.setBlockSet(blockMap);
            pageDAO.savePage(pageDO);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of savePages method");

    }

    /**
     * saveBlocks is getting setting blockDO object from blockForm object. pass
     * pageDO to PageDAO saveBlock method.
     *
     * @param blockForm
     * @throws java.lang.Exception
     */
    public void saveBlocks(BlockForm blockForm) throws Exception {
        adminServiceLogger.info("Going to run  AdminService saveBlocks method");
        try {
            BlockDO blockDO = new BlockDO();
            blockDO.setBlockId(blockForm.getBlockId());
            blockDO.setBlockTitle(blockForm.getBlockTitle());
            java.util.Date date = new java.util.Date();
            blockDO.setCreationDate(date);
            blockDO.setModificationDate(date);

            Map<Integer, Integer> questionMap = new HashMap<>();

            for (int questionId : blockForm.getQuestionSet()) {
                questionMap.put(questionId, questionId);
            }

            blockDO.setQuestionSet(questionMap);
            blockDAO.saveBlock(blockDO);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of saveBlocks method");
    }

    /**
     * retrieveBlocks method is for getting blockDOList from adminDAO set
     * blockDOList set to blockFormList
     *
     * @return blockFormList
     * @throws java.lang.Exception
     */
    public List<BlockForm> listBlocks() throws Exception {
        List<BlockForm> blockFormList = null;
        try {

            adminServiceLogger.info("Going to run  AdminService retrieveBlocks method");

            blockFormList = new ArrayList<>();

            List<BlockDO> blockDOList = blockDAO.getAllBlocks();

            for (BlockDO block : blockDOList) {

                BlockForm blockForm = new BlockForm();
                blockForm.setBlockId(block.getBlockId());
                blockForm.setBlockTitle(block.getBlockTitle());
                // System.out.println("The blocks are"+block.getQuestionSet());
                blockForm.setQuestionSetMap(block.getQuestionSet());

                blockFormList.add(blockForm);
            }

        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of retrieveBlocks method");
        return blockFormList;
    }

    /**
     * retrieveBlocks method is for getting pagesDOList from adminDAO set
     * pagesDOList set to pageFormList
     *
     * @return pageFormList
     * @throws java.lang.Exception
     */
    public List<PageForm> listPages() throws Exception {
        adminServiceLogger.info("Going to run  AdminService retrievePages method");
        List<PageForm> pageFormList = null;
        try {

            pageFormList = new ArrayList<>();

            List<PageDO> pagesDOList = pageDAO.findAllPages();

            Map<Integer, Integer> blockSetMap = new HashMap<>();

            for (PageDO page : pagesDOList) {
                PageForm pageForm = new PageForm();
                pageForm.setPageId(page.getPageId());
                pageForm.setPageTitle(page.getPageTitle());
                pageForm.setBlockSetMap(page.getBlockSet());
                pageFormList.add(pageForm);
            }

        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of retrievePages method");
        return pageFormList;
    }

    /**
     * removePage method get pageId from AdminQuestionController and send to
     * AdminDAO
     *
     * @param pageId
     * @throws java.lang.Exception
     */
    public void removePage(int pageId) throws Exception {
        adminServiceLogger.info("Going to run  AdminService removePage method");
        try {

            pageDAO.deletePage(pageId);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of removePage method");
    }

    /**
     * removeBlock method get pageId from AdminQuestionController and send to
     * AdminDAO
     *
     * @param blockId
     * @throws java.lang.Exception
     */
    public void removeBlock(int blockId) throws Exception {
        adminServiceLogger.info("Going to run  AdminService removeBlock method");
        try {

            blockDAO.deleteBlockByBlockId(blockId);
        } catch (Exception e) {

        }
        adminServiceLogger.info(" AdminService End of removeBlock method");
    }

    /**
     * removeRules method get questionId from AdminQuestionController and send
     * to AdminDAO
     *
     * @param questionId
     * @param choice
     * @throws java.lang.Exception
     */
    public void removeRules(int questionId, String choice) throws Exception {
        adminServiceLogger.info("Going to run  AdminService removeRules method");
        try {
            rulesDAO.deleteRule(questionId, choice);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of removeRules method");
    }

    /**
     * updatedQuestions method get blockForm from AdminQuestionController , set
     * adminQuestionForm to blockDO send to AdminDAO updateBlock Method.
     *
     * @param blockForm
     * @throws java.lang.Exception
     */
    public void updatedBlocks(BlockForm blockForm) throws Exception {
        try {

            adminServiceLogger.info("Going to run  AdminService updatedBlocks method");

            BlockDO blockDO = new BlockDO();
            blockDO.setBlockId(blockForm.getBlockId());
            blockDO.setBlockTitle(blockForm.getBlockTitle());
            Map<Integer, Integer> questionMap = new HashMap<>();

            for (int questionId : blockForm.getQuestionSet()) {
                questionMap.put(questionId, questionId);
            }

            blockDO.setQuestionSet(questionMap);
            blockDAO.updateBlock(blockDO);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of  updatedBlocks method");
    }

    /**
     * updatedPages method get pageForm from AdminQuestionController , set
     * adminQuestionForm to pageDO send to AdminDAO updatePage Method.
     *
     * @param pageForm
     * @throws java.lang.Exception
     */
    public void updatedPages(PageForm pageForm) throws Exception {
        adminServiceLogger.info("Going to run  AdminService updatedPages method");
        try {
            PageDO pageDO = new PageDO();
            pageDO.setPageId(pageForm.getPageId());
            pageDO.setPageTitle(pageForm.getPageTitle());

            Map<Integer, Integer> blockMap = new HashMap<>();

            for (int blockId : pageForm.getBlockSet()) {
                blockMap.put(blockId, blockId);
            }

            pageDO.setBlockSet(blockMap);
            pageDAO.updatePage(pageDO);
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("GAdminService End of updatedPages method");

    }

    /**
     * updatedRules method get rulesEngineForm from AdminQuestionController ,
     * set adminQuestionForm to rulesEngineDO send to AdminDAO updateRulesData
     * Method.
     *
     * @param rulesEngineForm
     * @throws java.lang.Exception
     */
    public void updatedRules(RulesEngineForm rulesEngineForm) throws Exception {
        adminServiceLogger.info("Going to run  AdminService updatedRules method");
        try {

            for (RuleForm rule : rulesEngineForm.getRuleForm()) {
                RulesEngineDO rulesEngineDO = new RulesEngineDO();
                rulesEngineDO.setChoice(rule.getChoiceName());
                rulesEngineDO.setQuestionId(Integer.parseInt(rule.getQuestionId()));
                rulesEngineDO.setRemarks(rule.getRemarks());
                rulesEngineDO.setScoring(rule.getScoring());
                java.util.Date date = new java.util.Date();
                rulesEngineDO.setCreationDate(date);
                rulesEngineDO.setModificationDate(date);

                rulesDAO.saveRule(rulesEngineDO);

            }
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of updatedRules method");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        /*adminService.initializeQuestion();
        adminService.initializeRulesEngine();
        adminService.postQuestions(adminQuestionForm);
        adminService.postRulesEngine(rulesEngineForm);
        adminService.removeBlock(0);
        adminService.removePage(0);
        adminService.removeQuestion(0);
        adminService.saveBlocks(blockForm);
        adminService.retrieveQuestions();
        adminService.savePages(pageForm);
        adminService.updatedBlocks(blockForm);
        adminService.updatedPages(pageForm);
        adminService.updatedQuestions(adminQuestionForm);
        adminService.updatedRules(rulesEngineForm);*/

    }

    public Map<Integer, String> findAllBlocks() {
        Map<Integer, String> blockMap = new HashMap<>();
        try {

            List<BlockDO> blockDOList = blockDAO.getAllBlocks();
            for (BlockDO blockDO : blockDOList) {
                blockMap.put(blockDO.getBlockId(), blockDO.getBlockTitle());

            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blockMap;
    }

    public int lastPageId() {
        int lastPage = 0;
        try {
            PageDO findLastPage = pageDAO.findLastPage();
            lastPage = findLastPage.getPageId();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastPage;
    }

    public int lastBlockId() {
        int lastBlock = 0;
        try {
            BlockDO findLastBlock = questionDAO.findLastBlock();
            lastBlock = findLastBlock.getBlockId();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastBlock;
    }

    public int lastQuestionId() {
        int lastQuestion = 0;
        try {
            QuestionDO findLastQuestion = questionDAO.findLastQuestion();
            lastQuestion = findLastQuestion.getQuestionId();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastQuestion;
    }

    public int lastRulesId() {
        int lastQuestion = 0;
        try {
            RulesEngineDO findLastRule = rulesDAO.findLastRule();
            // lastQuestion = findLastRule.getQuestionId();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastQuestion;
    }

    public List<AdminQuestionForm> retrieveOptions(int questionId) {
        adminServiceLogger.info("Going to run  AdminService retrieveQuestions method");
        List<AdminQuestionForm> adminQuestionFormList = null;
        try {

            adminQuestionFormList = new ArrayList<>();
            // 
            // System.out.println("&&&&&&&&&&& from  " + questionId);

            AdminQuestionDO adminQuestionDOList = questionDAO.findAllOptions(questionId);

            AdminQuestionForm adminQuestionForm = new AdminQuestionForm();
            adminQuestionForm.setOptions(adminQuestionDOList.getOptions());

            adminQuestionFormList.add(adminQuestionForm);

        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info(" AdminService End of retrieveQuestions method");
        return adminQuestionFormList;
    }

    public List<CloudAvailableDO> getAvailableRules() {
        adminServiceLogger.info("Going to run  AdminService getAvailableRules method");
        List<CloudAvailableDO> cloudAvailableDO = null;
        try {
            //rulesDAO.updateRule(rulesEngineDO);
            cloudAvailableDO = cloudAvailableDAO.getAvailableClouds();
        } catch (Exception e) {
            adminServiceLogger.error("This is Error message", e);

        }
        adminServiceLogger.info("AdminService End of getAvailableRules method");
        return cloudAvailableDO;

    }

}
