
package org.amortizer.loancalcservice;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.amortizer.loancalcservice.email.EmailManager;
import org.amortizer.loancalcservice.loancalcs.LoanCalculator;

/**
 * LoanCalcServiceSkeleton java skeleton for the axisService
 */

public class LoanCalcServiceSkeletonImpl implements LoanCalcServiceSkeletonInterface {

	static private ConcurrentHashMap<String, Integer> emailStore = new ConcurrentHashMap<String, Integer>();

	@Override
	public org.amortizer.loancalcservice.CalcLoanTermResponse calcLoanTerm(
			org.amortizer.loancalcservice.CalcLoanTerm calcLoanTrm) {

		double loanPmt = calcLoanTrm.getLoanPmt();
		double loanRate = calcLoanTrm.getLoanRate();
		double loanAmt = calcLoanTrm.getLoanAmt();

		LoanCalculator lc = new LoanCalculator(loanPmt, loanAmt, loanRate);

		lc.calcLoanTerm();

		CalcLoanTermResponse calcLoanTermResponseDoc = new CalcLoanTermResponse();

		calcLoanTermResponseDoc.setLoanTerm(lc.getTerm());

		return calcLoanTermResponseDoc;

	}

	@Override
	public org.amortizer.loancalcservice.CalcLoanRateResponse calcLoanRate(
			org.amortizer.loancalcservice.CalcLoanRate calcLoanRate) {

		double loanPmt = calcLoanRate.getLoanPmt();
		double loanAmt = calcLoanRate.getLoanAmt();
		int loanTerm = calcLoanRate.getLoanTerm();

		LoanCalculator lc = new LoanCalculator(loanTerm, loanPmt, loanAmt);

		lc.calcLoanIntRt();

		CalcLoanRateResponse calcLoanRateResponseDoc = new CalcLoanRateResponse();

		calcLoanRateResponseDoc.setLoanRate(lc.getRate());

		return calcLoanRateResponseDoc;

	}

	@Override
	public org.amortizer.loancalcservice.CalcLoanPmtResponse calcLoanPmt(
			org.amortizer.loancalcservice.CalcLoanPmt calcLoanPmt) {

		double loanAmt = calcLoanPmt.getLoanAmt();
		double loanRate = calcLoanPmt.getLoanRate();
		int loanTerm = calcLoanPmt.getLoanTerm();

		LoanCalculator lc = new LoanCalculator(loanAmt, loanRate, loanTerm);

		lc.calcLoanPmt();

		CalcLoanPmtResponse calcLoanPmtResponseDoc = new CalcLoanPmtResponse();

		calcLoanPmtResponseDoc.setLoanPmt(lc.getLoanPmt());

		return calcLoanPmtResponseDoc;

	}

	@Override
	public org.amortizer.loancalcservice.CalcLoanAmtResponse calcLoanAmt(
			org.amortizer.loancalcservice.CalcLoanAmt calcLoanAmt) {

		double loanPmt = calcLoanAmt.getLoanPmt();
		double loanRate = calcLoanAmt.getLoanRate();
		int loanTerm = calcLoanAmt.getLoanTerm();

		LoanCalculator lc = new LoanCalculator(loanPmt, loanTerm, loanRate);

		lc.calcLoanAmt();

		CalcLoanAmtResponse calcLoanAmtResponseDoc = new CalcLoanAmtResponse();

		calcLoanAmtResponseDoc.setLoanAmt(lc.getLoanAmt());

		return calcLoanAmtResponseDoc;

	}

	@Override
	public org.amortizer.loancalcservice.CalcLoanPayoffResponse calcLoanPayoff(
			org.amortizer.loancalcservice.CalcLoanPayoff calcLoanPayoff8) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#calcLoanPayoff");
	}

	@Override
	public org.amortizer.loancalcservice.ShowAmortTableResponse showAmortTable(
			org.amortizer.loancalcservice.ShowAmortTable showAmortTable10) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#showAmortTable");
	}

	@Override
	public VerifyEmailPINResponse verifyEmailPIN(VerifyEmailPIN verifyEmailPIN) {

		String email = verifyEmailPIN.getEmailAddress();
		String pinC = verifyEmailPIN.getEmailPIN();
		VerifyEmailPINResponse response = new VerifyEmailPINResponse();

		if (emailStore.get(email) != null && emailStore.get(pinC) != null) {
			Integer pinNoC = new Integer(verifyEmailPIN.getEmailPIN());
			Integer pinNoS = new Integer(emailStore.get(email));
			if (pinNoC == pinNoS) {
				response.setResponse("SUCCESS");
			}
		} else {
			response.setResponse("Check the email and pin submitted.");
		}

		return response;
	}

	@Override
	public DeleteEmailPINResponse deleteEmailPIN(DeleteEmailPIN deleteEmailPIN) {
		return null;
	}

	@Override
	public CheckEmailResponse checkEmail(CheckEmail checkEmail) {

		// generate 8 digit pin
		// email pin to user address
		// EmailManager(String subject, String toAddress, String fromAddress, String
		// contentMsg)
		Integer pin = getPIN();
		EmailManager emailMgr = null;
		try {
			emailMgr = new EmailManager("Loan Amortization Schedule: 2-Step Validation", checkEmail.getEmailAddress(),
					"info@LoanCalc.com", String.format("Here is your pin: %d", pin));
			emailMgr.sendEmailWithoutAttachement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// store email/pin combo for final verification
		emailStore.put(checkEmail.getEmailAddress(), pin);

		// return message to user
		CheckEmailResponse response = new CheckEmailResponse();
		response.setResponse(String.format("Check you email at %s for a pin to complete your email validation.",
				checkEmail.getEmailAddress()));

		return response;
	}

	private int getPIN() {
		Double randomNo = Math.random() * Math.random() * Math.pow(10, 8);

		while (!(randomNo < 100000000 && randomNo >= 10000000)) {
			randomNo = Math.random() * Math.pow(10, 9);
		}
		return randomNo.intValue();
	}

	static public void main(String args[]) {

		//System.out.println(new LoanCalcServiceSkeleton.getPIN());

	}
}
