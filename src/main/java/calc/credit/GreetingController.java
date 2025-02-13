package calc.credit.servingwebcontent;

import calc.credit.Credit;
import calc.credit.Plan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("sumCd","0");
        model.put("prcCd","0");
        model.put("period","0");
  /*
      <header>
        <nav>
            <ul class="menu">
                <li class="active">
                    <a href="/">Главная</a>
                </li>
                <li class="">
                    <a href="/greeting">О нас</a>
                </li>
            </ul>
        </nav>
    </header>
   */
        /*

         */

        return "main";
    }



    @PostMapping
    public String add (@RequestParam String sumCd, @RequestParam String prcCd, @RequestParam String period, Map<String, Object> model){
        Credit credit = new Credit();
        Iterable<Plan> plan;
        LocalDate dateBegin = LocalDate.of(2024, 1, 1);
        credit.setDateBegin(dateBegin);
        credit.setPeriod(Integer.valueOf(period));
        credit.setSumCredit(new BigDecimal(sumCd));
        credit.setPercentCredit(new BigDecimal(prcCd));
        credit.calcAnnuity();
        credit.calcPlan(new BigDecimal("10000"),5);

        plan = credit.getPlanOperations().values();

        model.put("plan",plan);
        model.put("sumCd",sumCd);
        model.put("prcCd",prcCd);
        model.put("period",period);
        return "main";
    }

}