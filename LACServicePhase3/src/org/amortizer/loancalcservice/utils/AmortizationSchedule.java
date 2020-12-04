package org.amortizer.loancalcservice.utils;



public class AmortizationSchedule {
	
	public AmortizationSchedule() {
		
	}
	
	public String createAmortizationSchedule(double loanAmt, double loanPmt, double rate, int term, double downPmt) {

		double balance = loanAmt;
		double payment = loanPmt;
		double monthlyInterest = 0;
		double principal = 0;

		StringBuilder amortSchedule = new StringBuilder();

		amortSchedule.append("Month       Payment       Principal       Interest        Balance");
		amortSchedule.append("\n");

		for (int i = 0; i < term; i++) {

			monthlyInterest = calcMonthlyInterest(balance, rate);
			principal = calculatePrincipal(payment, monthlyInterest);
			balance = calculateBalance(balance, downPmt, principal);

			amortSchedule.append(String.format("%5d", i+1));
			amortSchedule.append(String.format("%14.2f",payment));      
			amortSchedule.append(String.format("%16.2f",principal));     
			amortSchedule.append(String.format("%15.2f", monthlyInterest));     
			amortSchedule.append(String.format("%15.2f",balance));
			
			amortSchedule.append("\n");

		}
		return amortSchedule.toString();

		

	}

	private double calculatePrincipal(double loanPmt, double monthlyInterest) {
	
		return loanPmt - monthlyInterest;
	
	}

	private double calculateBalance(double loanAmt, double downPmt, double principal) {
	
		return (loanAmt - downPmt - principal);
	}

	private double calcMonthlyInterest(double balance, double intRate) {
	
		return balance * (intRate / (12 * 100));
	}

}
