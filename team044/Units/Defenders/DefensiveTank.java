package team044.Units.Defenders;

import battlecode.common.*;
import team044.Units.DefensiveUnits;
import team044.*;
import team044.Units.Tank;

public class DefensiveTank extends DefensiveUnits
{
    public DefensiveTank(RobotController rc)
    {
        super(rc);
        rc.setIndicatorString(0, "Defensive Tank");
    }

    public void collectData2() throws GameActionException
    {
        target = Utilities.getTowerClosestToEnemyHQ(rc);


        rc.setIndicatorString(1, "Target: " + target);
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        if (rc.readBroadcast(Messaging.Attack.ordinal()) == 1)
        {
            return new Tank(rc);
        }
        return current;
    }
}