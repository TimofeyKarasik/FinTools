package calc.credit;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter

public class Deposit {

    private BigDecimal sumDeposit;
    private BigDecimal percentDeposit;
    private int period;
    private int datePay;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private HashMap<Integer,Plan> planOperations = new HashMap<Integer,Plan>();

    void calcPlan(BigDecimal sumDop, Integer periodDop){
        Integer cnt = 0;
        Integer cntPlan = 0;
        BigDecimal tmpOdSumToCalc = new BigDecimal("0");
        BigDecimal tmpPercentSum = new BigDecimal("0");
        BigDecimal tmpOdSum = new BigDecimal("0");
        BigDecimal sumDopAll = new BigDecimal("0");
        int cntPeriod = this.getPeriod();
        boolean doCalc = false;
        if ( this.getSumDeposit().compareTo(new BigDecimal("0")) > 0){
            doCalc = true;
            if ( !this.planOperations.isEmpty()){
                this.planOperations.clear();
            }
            tmpOdSumToCalc = this.getSumDeposit();//  Math.ceil(this.getSumCredit() * 100)/100;
        }
        while (doCalc){
            cnt++;
            cntPeriod--;
            cntPlan++;
            Plan tmpPlan = new Plan(this.getDateBegin().plusMonths(cnt),null,null);
            tmpPercentSum = tmpPlan.calcPercent(this.getDateBegin().plusMonths(cnt-1),this.getDateBegin().plusMonths(cnt),tmpOdSumToCalc,this.getPercentDeposit());
            tmpOdSumToCalc = tmpOdSumToCalc.add(tmpPercentSum);
           // tmpOdSum = this.getAnnuity().subtract(tmpPercentSum);

            tmpPlan.setPercentSum(tmpPercentSum);
            tmpPlan.setOdSum(tmpOdSumToCalc);
            //Plan tmpPlan = new Plan(this.getDateBegin().plusMonths(cnt),tmpPercentSum,tmpOdSum);


            System.out.print(cnt);
            tmpPlan.println();
            this.planOperations.put(cntPlan,tmpPlan);

            if(periodDop > 0){
                if (cnt%periodDop == 0){
                    Plan tmpPlan2 = new Plan(this.getDateBegin().plusMonths(cnt),new BigDecimal("0"),sumDop);
                    cntPlan++;
                    tmpOdSumToCalc = tmpOdSumToCalc.add(sumDop);
                    sumDopAll = sumDopAll.add(sumDop);
                    System.out.print(cnt);
                    tmpPlan2.println();
                    this.planOperations.put(cntPlan,tmpPlan2);
                }
            }


            if (cntPeriod == 0){

                System.out.println("!!!!!!!!!!tmpOdSumToCalc = "+tmpOdSumToCalc);
                System.out.println("!!!!!!!!!!sumDopAll = "+sumDopAll);
                System.out.println("!!!!!!!!!!sumPercent = "+tmpOdSumToCalc.subtract(sumDopAll));
                doCalc = false;
            }
        }
    }


}
