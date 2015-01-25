package _teamlauncherTimingAttack.Structures;

import battlecode.common.*;
import _teamlauncherTimingAttack.Messaging;
import _teamlauncherTimingAttack.Structure;
import _teamlauncherTimingAttack.Utilities;

import java.util.Random;

public class Barracks extends Structure
{
    boolean basher = true;
    int numbOfSoldiers = 0;
    int counter = 0;
    Random random;
    public Barracks(RobotController rc)
    {
        super(rc);
        if (rc.getLocation().distanceSquaredTo(rc.senseHQLocation()) > 35)
        {
            basher = true;
        }

        random = new Random(rc.getID());
        rc.setIndicatorString(0, "Barracks");
    }

    // overridden methods go here

    public void collectData() throws GameActionException
    {
        // collect our data
        super.collectData();
        numbOfSoldiers = rc.readBroadcast(Messaging.NumbOfSoldiers.ordinal());
        if (random.nextInt(4) < 1)
        {
            basher = false;
        }
        else
        {
            basher = true;
        }
    }

    public boolean carryOutAbility() throws GameActionException
    {
        if (basher && Utilities.spawnUnit(RobotType.BASHER, rc))
        {
            return true;
        }
        else if (!basher && Utilities.spawnUnit(RobotType.SOLDIER, rc))
        {
            return true;
        }

        /*
        if (rc.readBroadcast(Messaging.ShutOffBasherProd.ordinal()) == 0)
        {
            if (Utilities.spawnUnit(RobotType.BASHER, rc))
            {
                counter++;
                return true;
            }
        }
        else if (rc.readBroadcast(Messaging.ShutOffSoldierProd.ordinal()) == 0)
        {
            if (Utilities.spawnUnit(RobotType.SOLDIER, rc))
            {
                counter++;
                return true;
            }
        }
        */
        return false;
    }
}
