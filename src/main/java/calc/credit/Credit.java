package calc.credit;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

@Getter
@Setter

public class Credit {
    private BigDecimal sumCredit;
    private BigDecimal percentCredit;
    private int period;
    private BigDecimal annuity;
    private int datePay;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private HashMap<Integer,Plan> planOperations = new HashMap<Integer,Plan>();

    public void calcAnnuity(){
        BigDecimal percent;


        percent = this.percentCredit;
        percent = percent.divide(new BigDecimal("1200"),10, RoundingMode.HALF_UP).add(new BigDecimal("1")).pow(this.period);
        this.annuity = this.sumCredit;
        this.annuity = this.annuity.multiply(this.percentCredit).divide(new BigDecimal("1200"),10, RoundingMode.HALF_UP).multiply(percent);
        percent = percent.subtract(new BigDecimal("1"));

        this.annuity = this.annuity.divide(percent,2,RoundingMode.HALF_UP);
        //System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        /*
        this.annuity = this.sumCredit * this.percentCredit/1200
        * Math.pow((1+this.percentCredit/1200),this.period)
        /(Math.pow((1+this.percentCredit/1200),this.period) -1);
        this.annuity = Math.ceil(this.annuity * 100)/100;
         */
    }



    public void calcPlan(BigDecimal sumDop, Integer periodDop){
        Integer cnt = 0;
        Integer cntPlan = 0;
        BigDecimal tmpOdSumToCalc = new BigDecimal("0");
        BigDecimal tmpPercentSum = new BigDecimal("0");
        BigDecimal tmpOdSum = new BigDecimal("0");
        BigDecimal sumPercent  = new BigDecimal("0");
        BigDecimal sumOdSum  = new BigDecimal("0");
        int cntPeriod = this.getPeriod();
        boolean doCalc = false;
        if (this.getAnnuity().compareTo(new BigDecimal("0")) > 0 && this.getSumCredit().compareTo(new BigDecimal("0")) > 0){
            doCalc = true;
            if ( !this.planOperations.isEmpty()){
                this.planOperations.clear();
            }
            tmpOdSumToCalc = this.getSumCredit();//  Math.ceil(this.getSumCredit() * 100)/100;
        }
        while (doCalc){
            cnt++;
            cntPeriod--;
            cntPlan++;
            Plan tmpPlan = new Plan(this.getDateBegin().plusMonths(cnt),null,null);
            tmpPercentSum = tmpPlan.calcPercent(this.getDateBegin().plusMonths(cnt-1),this.getDateBegin().plusMonths(cnt),tmpOdSumToCalc,this.getPercentCredit());

            tmpOdSum = this.getAnnuity().subtract(tmpPercentSum);

            if (tmpOdSumToCalc.subtract(tmpOdSum).compareTo(new BigDecimal("0")) <= 0)
            {
                tmpOdSum = tmpOdSumToCalc;
                tmpOdSumToCalc = new BigDecimal("0");
            }
            else {
                tmpOdSumToCalc = tmpOdSumToCalc.subtract(tmpOdSum);
            }

            tmpPlan.setPercentSum(tmpPercentSum);
            tmpPlan.setOdSum(tmpOdSum);
            sumPercent = sumPercent.add(tmpPercentSum);
            sumOdSum = sumOdSum.add(tmpOdSum);
            //Plan tmpPlan = new Plan(this.getDateBegin().plusMonths(cnt),tmpPercentSum,tmpOdSum);



            System.out.print(cnt);
            tmpPlan.println();
            this.planOperations.put(cntPlan,tmpPlan);


            if ((periodDop > 0)&&(tmpOdSumToCalc.compareTo(new BigDecimal("0")) > 0))
            {
                if (cnt%periodDop == 0){
                    Plan tmpPlan2 = new Plan(this.getDateBegin().plusMonths(cnt),new BigDecimal("0"),sumDop);
                    cntPlan++;

                  //  tmpOdSumToCalc = tmpOdSumToCalc.subtract(sumDop);

                    if (tmpOdSumToCalc.subtract(sumDop).compareTo(new BigDecimal("0")) <= 0)
                    {
                        tmpOdSum = tmpOdSumToCalc;
                        tmpOdSumToCalc = new BigDecimal("0");
                    }
                    else {
                        tmpOdSumToCalc = tmpOdSumToCalc.subtract(sumDop);
                        tmpOdSum = sumDop;
                    }

                    tmpPlan2.setPercentSum(new BigDecimal("0"));
                    tmpPlan2.setOdSum(tmpOdSum);

                    System.out.print(cnt);
                    tmpPlan2.println();
                    this.planOperations.put(cntPlan,tmpPlan2);
                }
            }
/*
    <#list plan as plan>
        <div>
            <b>${planData}</b>
            <span>${percentSum}</span>
            <i>${odSum}</i>
        </div>
    </#list>

 */

            if ((cntPeriod == 0)||(tmpOdSumToCalc.compareTo(new BigDecimal("0")) == 0))
            {
                System.out.println("!!!!!!!!!!sumPercent = "+sumPercent);
                System.out.println("!!!!!!!!!!sumOdSum = "+sumOdSum);
                System.out.println("!!!!!!!!!!tmpOdSumToCalc = "+tmpOdSumToCalc);
                doCalc = false;
            }
        }
    }
/*
    Sе = S*P /12*(1+P/12)N/((1+P/12)N -1), где S – сумма кредита
, P – размер годовой процентной ставки
, N – количество месяцев, в течение которых производятся выплаты.
            */
}
