package org.example;

public class FinanceSimulator {
    /*
    現金初期値：initialCash               (例：1000000)
    住宅ローン残高初期値：initialLoan     (例：1000000)
    年間貯金増加：annualIncomeIncrease      (例：500000 ≒ 年50万)
    ローン金利：loanInterestRate          (例：0.004 = 0.4%)
    投資利回り：investmentYieldAnnual     (例：0.03～0.05)
    毎月のローン返済額：monthlyLoanPayment (例：100000)
    シミュレーション年数：years          (例：10)
    初月に返済する金額：payAtFirstMonth
     */
    public static void simulate(
            double initialCash,
            double initialLoan,
            double annualIncomeIncrease,
            double loanInterestRate,
            double investmentYieldAnnual,
            int years,
            double payAtFirstMonth,
            double monthlyInvestmentAmount
    ) {
        final double CASH_FLOOR = 1_000_000;  // ← キャッシュ下限 100万円

        double cash = initialCash;
        double loan = initialLoan;
        double investment = 0.0;

        double monthlyIncome = annualIncomeIncrease / 12.0;
        double loanInterestMonthly = loanInterestRate / 12.0;
        double investmentYieldMonthly = investmentYieldAnnual / 12.0;

        int totalMonths = years * 12;

        // ★ 元金均等返済（40年）
        int loanTermMonths = 40 * 12;
        double principalPayment = initialLoan / loanTermMonths;  // ←毎月一定の元金返済額

        System.out.println("Month,Cash,Loan,Invest,TotalNetWorth");

        for (int month = 1; month <= totalMonths; month++) {

            // --- 初月にローン全額返済する戦略 ---
            if (month == 1) {
                cash -= payAtFirstMonth;
                loan -= payAtFirstMonth;
            }

            // ① 現金に収入追加
            cash += monthlyIncome;

            double interest = 0;

            // ② 利息計算（元金均等）
            if (loan > 0) {
                interest = loan * loanInterestMonthly;
                loan += interest;  // 一旦利息を乗せる
            }

            // ③ 元金返済 + 利息支払
            if (loan > 0) {
                double principal = Math.min(principalPayment, loan);  // 最終月対策
                double totalPayment = principal + interest;

                cash -= totalPayment;
                loan -= principal;  // 元金だけ減らす（利息分は②で反映済み）
            }

            // ④ 投資複利成長
            investment *= (1 + investmentYieldMonthly);

            // ⑤ 投資積立（100万円を割らない範囲で）
            if (monthlyInvestmentAmount > 0) {
                if (cash - monthlyInvestmentAmount >= CASH_FLOOR) {
                    cash -= monthlyInvestmentAmount;
                    investment += monthlyInvestmentAmount;
                }
                // 割る場合は積立しない
            }

            // CSV 出力
            System.out.printf("%d,%.2f,%.2f,%.2f,%.2f%n",
                    month, cash, loan, investment, cash + investment - loan);
        }
    }


    public static void main(String[] args) {
        simulate(
                1_000_000, // 現金100万
                1_000_000, // ローン100万
                500_000,  // 年間収入増分
                0.004,      // ローン金利
                0.04,       // 投資利回り 4%
                9,         // 10年
                0,      // 初月に全部返済?
                100_000     // 毎月投資
        );
    }
}

