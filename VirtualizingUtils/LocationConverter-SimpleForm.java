package de.xunify.virtualizingutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationConverter {

public static String getStringFromLocation(Location location) {
		return location.getWorld().getName()+"; "+location.getX()+"; "+location.getY()+"; "+location.getZ()+"; "+location.getYaw()+"; "+location.getPitch();
	}
	
public static Location getLocationFromString(String locationstring) {
		String[] array = locationstring.split("; ");
		Location loc = new Location(Bukkit.getWorld(array[0]), Double.valueOf(array[1]), Double.valueOf(array[2]), Double.valueOf(array[3]));
		loc.setYaw(Float.valueOf(array[4]));
		loc.setPitch(Float.valueOf(array[5]));
		return loc;
	}

}
