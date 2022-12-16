package util;

import java.awt.Rectangle;

import Object.Object;
import entity.Entity;
import projectOOP_1.GamePanel;
import projectile.Projectile;

public class Collision {
	private GamePanel gp;
	public Collision(GamePanel gp) {
		this.gp = gp;
	}
	
	public void check(Entity entity) {
		int leftWorldX = entity.getWorldX() + entity.getSolidArea().x;
		int rightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
		int topWorldY = entity.getWorldY() + entity.getSolidArea().y;
		int bottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;
		
		int leftCol = leftWorldX / gp.TILE_SIZE;
		int rightCol = rightWorldX / gp.TILE_SIZE;
		int topRow = topWorldY / gp.TILE_SIZE;
		int bottomRow = bottomWorldY / gp.TILE_SIZE;
		
		int num1, num2;
		
		switch(entity.getDirection()) {
		case "N": 
			topRow = (topWorldY - entity.getSpeed())/gp.TILE_SIZE;
		    num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][topRow];
		    num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][topRow];
		    if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
			    entity.setCollisionOn(true);
		    }
			break;
		case "S":  
			bottomRow = (bottomWorldY + entity.getSpeed())/gp.TILE_SIZE;
			num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][bottomRow];
			num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][bottomRow];
			if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "E":  
			leftCol = (leftWorldX - entity.getSpeed())/gp.TILE_SIZE;
			num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][topRow];
			num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][bottomRow];
			if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "W": 
			rightCol = (rightWorldX + entity.getSpeed())/gp.TILE_SIZE;
			num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][topRow];
			num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][bottomRow];
			if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "NE": 
			topRow = (topWorldY - entity.getSpeed())/gp.TILE_SIZE;
		    leftCol = (leftWorldX - entity.getSpeed())/gp.TILE_SIZE;
		    num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][topRow];
		    num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][topRow];
		    if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
			    entity.setCollisionOn(true);
		    }
			break;
		case "SE":  
			leftCol = (leftWorldX - entity.getSpeed())/gp.TILE_SIZE;
			bottomRow = (bottomWorldY + entity.getSpeed())/gp.TILE_SIZE;
			num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][bottomRow];
			num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][bottomRow];
			if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "NW":  
			topRow = (topWorldY - entity.getSpeed())/gp.TILE_SIZE;
			rightCol = (rightWorldX + entity.getSpeed())/gp.TILE_SIZE;
			num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][leftCol][topRow];
			num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][topRow];
			if (gp.getTileM().getTile()[num1].isCollision() == true ||  gp.getTileM().getTile()[num2].isCollision() == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "SW": 
			bottomRow = (bottomWorldY + entity.getSpeed())/gp.TILE_SIZE;
			rightCol = (rightWorldX + entity.getSpeed())/gp.TILE_SIZE;
			num1 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][topRow];
			num2 = gp.getTileM().getMapTile()[gp.getTileM().getCurrentMap()][rightCol][bottomRow];
			if (gp.getTileM().getTile()[num1].isCollision() == true || gp.getTileM().getTile()[num2].isCollision() == true) {
				entity.setCollisionOn(true);
			}
			break;
		}
	}
	
	public int checkEntity(Projectile entity, Entity[] destiny ) {
		int index=999;
		for (int i=0;i<destiny.length;i++) {
			if (destiny[i]!=null) {
				entity.getSolidArea().x = entity.getWorldX()+entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY()+entity.getSolidArea().y;
				
				destiny[i].getSolidArea().x = destiny[i].getWorldX()+destiny[i].getSolidArea().x;
				destiny[i].getSolidArea().y = destiny[i].getWorldY()+destiny[i].getSolidArea().y;
				
				switch(entity.getDirection()) {
				case "N":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "S":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "E":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "W":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "NE":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "SE":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "WE":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "WS":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				}
				entity.getSolidArea().x = 16;
				entity.getSolidArea().y = 16;
				destiny[i].getSolidArea().x = 16;
				destiny[i].getSolidArea().y = 16;
			}
		}
		return index;
	}
	
	public int checkEntity(Entity entity, Entity[] destiny ) {
		int index=999;
		for (int i=0;i<destiny.length;i++) {
			if (destiny[i]!=null) {
				entity.getSolidArea().x = entity.getWorldX()+entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY()+entity.getSolidArea().y;
				
				destiny[i].getSolidArea().x = destiny[i].getWorldX()+destiny[i].getSolidArea().x;
				destiny[i].getSolidArea().y = destiny[i].getWorldY()+destiny[i].getSolidArea().y;
				
				switch(entity.getDirection()) {
				case "N":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "S":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "E":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "W":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "NE":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "SE":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "WE":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				case "WS":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny[i].getSolidArea())) {
						index = i;
					}
					break;
				}
				entity.getSolidArea().x = 16;
				entity.getSolidArea().y = 16;
				destiny[i].getSolidArea().x = 16;
				destiny[i].getSolidArea().y = 16;
			}
		}
		return index;
	}
	
	public int checkEntity(Projectile entity, Entity destiny ) {
		int i=999;
				entity.getSolidArea().x = entity.getWorldX()+entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY()+entity.getSolidArea().y;
				
				destiny.getSolidArea().x = destiny.getWorldX()+destiny.getSolidArea().x;
				destiny.getSolidArea().y = destiny.getWorldY()+destiny.getSolidArea().y;
				
				switch(entity.getDirection()) {
				case "N":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "S":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "E":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						//entity.setCollisionOn(true);
						i=1;
					}
					break;
				case "W":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						//entity.setCollisionOn(true);
						i=1;
					}
					break;
				case "NW":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						//entity.setCollisionOn(true);
						i=1;
					}
					break;
				case "SE":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "NE":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "SW":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				}
				entity.getSolidArea().x = 16;
				entity.getSolidArea().y = 16;
				destiny.getSolidArea().x = 16;
				destiny.getSolidArea().y = 16;
				return i;
				
	}
	
	
	public int checkEntity(Entity entity, Entity destiny ) {
		int i=999;
				entity.getSolidArea().x = entity.getWorldX()+entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY()+entity.getSolidArea().y;
				
				destiny.getSolidArea().x = destiny.getWorldX()+destiny.getSolidArea().x;
				destiny.getSolidArea().y = destiny.getWorldY()+destiny.getSolidArea().y;
				
				switch(entity.getDirection()) {
				case "N":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "S":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "E":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "W":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "NW":
					entity.getSolidArea().y-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "SE":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "NE":
					entity.getSolidArea().x-=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				case "SW":
					entity.getSolidArea().y+=entity.getSpeed();
					if (entity.getSolidArea().intersects(destiny.getSolidArea())) {
						i=1;
					}
					break;
				}
				entity.getSolidArea().x = 16;
				entity.getSolidArea().y = 16;
				destiny.getSolidArea().x = 16;
				destiny.getSolidArea().y = 16;
				return i;
				
	}
	
	public int checkObject(Entity entity, Object[] keys) {
		int index = 999;
		
		for (int i = 0; i<keys.length; i++) {
			if (keys[i]!=null) {
				entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
				keys[i].getSolidArea().x = keys[i].getWorldX() + keys[i].getSolidArea().x;
				keys[i].getSolidArea().y = keys[i].getWorldY() + keys[i].getSolidArea().y;
				
				switch (entity.getDirection()) {
				case "N": entity.getSolidArea().y -= entity.getSpeed();
				    if(entity.getSolidArea().intersects(keys[i].getSolidArea())) {
				    	if (keys[i].isCollision()==true) {
				    		 entity.setCollisionOn(true);
				    	}
				    	index = i;
				    }
					break;
				case "S": entity.getSolidArea().y += entity.getSpeed();
				    if(entity.getSolidArea().intersects(keys[i].getSolidArea())) {
			    	if (keys[i].isCollision()==true) {
			    		 entity.setCollisionOn(true);
			    	}
			    	index = i;
			    }
					break;
				case "E": entity.getSolidArea().x-= entity.getSpeed();
				    if(entity.getSolidArea().intersects(keys[i].getSolidArea())) {
			    	if (keys[i].isCollision()==true) {
			    		 entity.setCollisionOn(true);
			    	}
			    	index = i;
			    }
					break;
				case "W":  entity.getSolidArea().x+= entity.getSpeed();
				    if(entity.getSolidArea().intersects(keys[i].getSolidArea())) {
			    	if (keys[i].isCollision()==true) {
			    		 entity.setCollisionOn(true);
			    	}
			    	index = i;
			    }
					break;
				}
				entity.getSolidArea().x = 16;
				entity.getSolidArea().y = 16;
				keys[i].setSolidArea(new Rectangle(0,0,48,48));
			}
		}
		return index;
	}
	
	public int checkObject(Entity entity, Object obj) {
		int index = 999;
			if (obj!=null) {
				entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
				obj.getSolidArea().x = obj.getWorldX() + obj.getSolidArea().x;
				obj.getSolidArea().y = obj.getWorldY() + obj.getSolidArea().y;
				
				switch (entity.getDirection()) {
				case "N": entity.getSolidArea().y -= entity.getSpeed();
				    if(entity.getSolidArea().intersects(obj.getSolidArea())) {
				    	if (obj.isCollision()==true) {
				    		 entity.setCollisionOn(true);
				    	}
				    	index = 1;
				    }
					break;
				case "S": entity.getSolidArea().y += entity.getSpeed();
				    if(entity.getSolidArea().intersects(obj.getSolidArea())) {
			    	if (obj.isCollision()==true) {
			    		 entity.setCollisionOn(true);
			    	}
			    	index = 1;
			    }
					break;
				case "E": entity.getSolidArea().x-= entity.getSpeed();
				    if(entity.getSolidArea().intersects(obj.getSolidArea())) {
			    	if (obj.isCollision()==true) {
			    		 entity.setCollisionOn(true);
			    	}
			    	index = 1;
			    }
					break;
				case "W":  entity.getSolidArea().x+= entity.getSpeed();
				    if(entity.getSolidArea().intersects(obj.getSolidArea())) {
			    	if (obj.isCollision()==true) {
			    		 entity.setCollisionOn(true);
			    	}
			    	index = 1;
			    }
					break;
				}
				entity.getSolidArea().x = 16;
				entity.getSolidArea().y = 16;
				obj.setSolidArea(new Rectangle(48,96,96,48));
			}
		return index;
	}
}



