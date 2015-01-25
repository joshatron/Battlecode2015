package _teamdroneSurround.Units.harrassers;

import battlecode.common.*;
import _teamdroneSurround.Messaging;
import _teamdroneSurround.Unit;
import _teamdroneSurround.Units.HarrasserUnit;
import _teamdroneSurround.Units.Rushers.TankRusher;
import _teamdroneSurround.Utilities;

public class TankHarrasser extends HarrasserUnit
{
    public TankHarrasser(RobotController rc)
    {
        super(rc);
    }

    public void handleMessages() throws GameActionException
    {
        super.handleMessages();

        Utilities.handleMessageCounter(rc, Messaging.NumbOfTanksOdd.ordinal(), Messaging.NumbOfTanksEven.ordinal());
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        if (rc.readBroadcast(Messaging.Attack.ordinal()) == 1)
        {
            return new TankRusher(rc);
        }
        return current;
    }
}
