package calc.credit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Credit credit = new Credit();
        Deposit deposit = new Deposit();

        //BigDecimal sumCredit = new BigDecimal("16189438.86");
        LocalDate dateBegin = LocalDate.of(2024, 1, 1);
        // <input type="number">
        //-- number

        credit.setDateBegin(dateBegin);
        credit.setPeriod(360);
        credit.setSumCredit(new BigDecimal("16189438.86"));
        credit.setPercentCredit(new BigDecimal("4.5"));
        credit.calcAnnuity();
        credit.setAnnuity(new BigDecimal("82247"));

        System.out.println(credit.getAnnuity());

        credit.calcPlan(new BigDecimal("10000"),5);

        credit.setPeriod(330);

        credit.calcPlan(new BigDecimal("10000"),0);

      //  credit.setPeriod(12);
    //    credit.calcPlan(new BigDecimal("10000"),0);

        System.out.println("*******************************************************");
        deposit.setDateBegin(dateBegin);
        deposit.setPeriod(330);
        deposit.setSumDeposit(new BigDecimal("1"));
        deposit.setPercentDeposit(new BigDecimal("14"));
        deposit.calcPlan(new BigDecimal("10000"),5);
/*
        System.out.println("*******************************************************");

        credit.setSumCredit(new BigDecimal("16189438.86"));
        credit.calcPlan(new BigDecimal("10000"),5);
*/

        /*
           <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.30</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.5.5.Final</version>
                        </path>
                    </annotationProcessorPaths>
                    <source>22</source>
                    <target>22</target>
                </configuration>
            </plugin>

         */

       // credit.calcPercent(LocalDate.of(2024, 1, 1),LocalDate.of(2024, 2, 1),credit.getSumCredit(),credit.getPercentCredit());
        //credit.calcPercent(LocalDate.of(2024, 2, 1),LocalDate.of(2024, 3, 1),credit.getSumCredit(),credit.getPercentCredit());
        /*

Sе = S*P /12*(1+P/12)N/((1+P/12)N -1), где S – сумма кредита
, P – размер годовой процентной ставки
, N – количество месяцев, в течение которых производятся выплаты.
 */

    }
}