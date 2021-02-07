
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Kenny Castro-Monroy
 * @version 2021.02.07
 */
public class ClockDisplay
{
    private boolean isAM;
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        this.isAM = true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        if(hour >= 12 && hour != 24)
        {
            this.isAM = false;
        } 
        else
        {
            this.isAM = true;
        }
        hours.setValue(hour % 12);
        minutes.setValue(minute);
        
        updateDisplay();
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            if(hours.getValue() == 11) // If hours is currently 11, it's going to be set to 12 which changes the meridian
            {
                this.isAM = this.isAM == true ? false : true; // if isAM is true, make it false, otherwise make it true 
            }
                
            hours.increment();
        }

        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);

        if(hour >= 12 && hour != 24)
        {
            this.isAM = false;
        } 
        else
        {
            this.isAM = true;
        }

        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     *  Gets the internal 12-hour representation of time
     * @return a string representing the time in 12-hour format(HH:MM)
     */
    public String get12HourInternalDisplay() 
    {
        return displayString;
    }

    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        String hour = hours.getValue() == 0 ? "12" : hours.getDisplayValue();
        this.displayString = hour + ":" + minutes.getDisplayValue() + (this.isAM ? " AM" : " PM");
    }
}
