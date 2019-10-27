/*    */ package thaumcraft.common.lib.network;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.PlayerEvent;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncKnowledge;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class EventHandlerNetwork
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
/* 19 */     Side side = FMLCommonHandler.instance().getEffectiveSide();
/* 20 */     if (side == Side.SERVER) {
/* 21 */       EntityPlayer p = event.player;
/* 22 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(p), (EntityPlayerMP)p);
/* 23 */       PacketHandler.INSTANCE.sendTo(new PacketSyncKnowledge(p), (EntityPlayerMP)p);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\EventHandlerNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */