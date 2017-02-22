package de.xunify.region.;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Region {

	private List<Location> locations;
	private Location pos1;
	private Location pos2;
	
	public Region(Location loc1, Location loc2) {
		this.pos1 = getMax(loc1, loc2);
		this.pos2 = getMin(loc1, loc2);
		
		loadLocations();
	}
	
  /*If you wanna ask if a Location (Player) is inside use this!*/
	public boolean isInside(Location loc) {
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		if(x < pos1.getBlockX() && x > pos2.getBlockX()) {
			if(z < pos1.getBlockZ() && z > pos2.getBlockZ()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Location> getLocations() {
		return locations;
	}
	
	/*Plugin Use Only
  * class needs these methods to get the region*/
	
	private Location getMax(Location loc1, Location loc2) {
		int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		return new Location(loc1.getWorld(), maxX, 90, maxZ);
	}
	
	private Location getMin(Location loc1, Location loc2) {
		int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		
		return new Location(loc1.getWorld(), minX, 90, minZ);
	}
	
	private void loadLocations() {
		locations = new ArrayList<>();
		for(int x = pos2.getBlockX(); x < pos1.getBlockX(); x++) {
			for(int z = pos2.getBlockZ(); z < pos1.getBlockZ(); z++) {
				for(int y = 0; y < 200; y++) {
					locations.add(new Location(pos1.getWorld(), x, y, z));
				}
			}
		}
	}
	

	
}
