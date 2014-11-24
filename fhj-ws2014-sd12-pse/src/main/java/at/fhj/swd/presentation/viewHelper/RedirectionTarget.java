package at.fhj.swd.presentation.viewHelper;

enum RedirectionTarget {

	LOGIN("login"),
	COMMUNITIES("communities");
	
	private String redirectionTarget;
	
	RedirectionTarget(String redirectionTarget) {
		this.redirectionTarget = redirectionTarget;
	}
	
	String getValue() {
		return this.redirectionTarget;
	}
}
