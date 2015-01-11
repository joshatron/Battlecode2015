package team044;

import battlecode.common.*;

/**
 * This class is for common behaviou
 */
public abstract class Structure extends Unit
{
    public Structure()
    {
        //do nothing
    }

    public Structure(RobotController rc)
    {
        this.rc = rc;
        us = rc.getTeam();
        opponent = us.opponent();
        range = rc.getType().attackRadiusSquared;
        sightRange = rc.getType().sensorRadiusSquared;
        tracker = new EnemyMinerTracker(rc);
        ourHQ = rc.senseHQLocation();
        enemyHQ = rc.senseEnemyHQLocation();
    }

    public void collectData() throws GameActionException
    {
        // collect our data
        super.collectData();

        enemies = rc.senseNearbyRobots(sightRange, opponent);
    }

    public void handleMessages() throws GameActionException
    {
        rc.setIndicatorString(1, "Handle Messages");
        if (enemies.length > 0)
        {
            rc.setIndicatorString(2, "Enemies spoted");
            rc.broadcast(Messaging.BuildingInDistressX.ordinal(), rc.getLocation().x);
            rc.broadcast(Messaging.BuildingInDistressY.ordinal(), rc.getLocation().y);
        }
    }

    // structures can't move!
    public boolean takeNextStep() throws GameActionException
    {
        return false;
    }

    // most structures can't fight will override for towers and HQ
    public boolean fight() throws GameActionException
    {
        return false;
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        return current;
    }

    public boolean carryOutAbility() throws GameActionException
    {
        return false;
    }

    // for structures even distribute supplies among all allies
    public void distributeSupply() throws  GameActionException
    {
        Utilities.shareSupplies(rc);
        /*int dist = GameConstants.SUPPLY_TRANSFER_RADIUS_SQUARED;

        if (rc.getSupplyLevel() == 0)
        {
            return;
        }

        if (rc == null)
        {
            System.out.println("Houston we have a serious problem");
        }
        rc.setIndicatorString(1, "");
        rc.setIndicatorString(2, "");

        RobotInfo[] closeAllies = rc.senseNearbyRobots(dist, us);
        if (closeAllies.length <= 0)
        {
            return;
        }

        int supplies = (int) (rc.getSupplyLevel() / closeAllies.length);

        for (int i = 0; i < closeAllies.length; i++)
        {
            if (Clock.getBytecodeNum() < 1000)
            {
                break;
            }
            if (closeAllies[i].type == RobotType.BEAVER)
            {
                continue;
            }
            if (closeAllies[i].type == RobotType.DRONE)
            {
                int totalSupplies = (int) rc.getSupplyLevel() / 2;
                rc.transferSupplies(totalSupplies, closeAllies[i].location);
                continue;
            }
            MapLocation ally = closeAllies[i].location;
            if (rc.isLocationOccupied(ally) && rc.getLocation().distanceSquaredTo(ally) < dist)
            {
                rc.transferSupplies(supplies, ally);
            }
        }*/
    }
}
