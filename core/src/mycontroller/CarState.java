package mycontroller;

public enum CarState {
	STOPPED  
	{
		@Override
		CarState event(float delta) {
			// TODO Auto-generated method stub
			return null;
		}
	}, 
	
	DRIVING  
	{
		@Override
		CarState event(float delta) {
			return DRIVING;
		}
	}, 
	
	REVERSING 
	{
		@Override
		CarState event(float delta) {
			
			return REVERSING;
		}
	};
	
	/** 
	 * An general event where the car goes though a situation 
	 * @param delta time passing by
	 * @return the new state of the ar
	 */
	abstract CarState event(float delta);
}
