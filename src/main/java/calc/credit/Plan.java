package calc.credit;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Setter

public class Plan {

        private LocalDate planData;
        private BigDecimal percentSum;
        private BigDecimal odSum;

        Plan(LocalDate planData,BigDecimal percentSum,BigDecimal odSum){
                this.planData = planData;
                this.percentSum = percentSum;
                this.odSum = odSum;
        }

        void println(){
                System.out.print("planData = "+this.planData);
                System.out.print(", percentSum = "+this.percentSum);
                System.out.print(", odSum = "+this.odSum);
                System.out.println();
        }

        BigDecimal calcPercent(LocalDate dateBeginCalc, LocalDate dateEndCalc, BigDecimal OdToCalc, BigDecimal percentCreditToCalc){
                BigDecimal  result;
                long resultDays = ChronoUnit.DAYS.between(dateBeginCalc, dateEndCalc) - 1;


                result = OdToCalc.multiply(percentCreditToCalc).divide(new BigDecimal("100"),10, RoundingMode.HALF_UP).divide(new BigDecimal("366"),2,RoundingMode.HALF_UP).multiply(new BigDecimal(resultDays));

                //result = resultDays * (OdToCalc * percentCreditToCalc/100) / 366; //TODO количество дней в году нужно считать
                // result = Math.ceil(result * 100)/100;

                return result;
        }

}
