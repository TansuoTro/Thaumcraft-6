package thaumcraft.api.aspects;

public interface IAspectContainer {
  AspectList getAspects();
  
  void setAspects(AspectList paramAspectList);
  
  boolean doesContainerAccept(Aspect paramAspect);
  
  int addToContainer(Aspect paramAspect, int paramInt);
  
  boolean takeFromContainer(Aspect paramAspect, int paramInt);
  
  @Deprecated
  boolean takeFromContainer(AspectList paramAspectList);
  
  boolean doesContainerContainAmount(Aspect paramAspect, int paramInt);
  
  @Deprecated
  boolean doesContainerContain(AspectList paramAspectList);
  
  int containerContains(Aspect paramAspect);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\IAspectContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */