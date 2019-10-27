/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchCategory;
/*    */ import thaumcraft.client.lib.events.HudHandler;
/*    */ import thaumcraft.client.lib.events.RenderEventHandler;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ 
/*    */ 
/*    */ public class PacketKnowledgeGain
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketKnowledgeGain, IMessage>
/*    */ {
/*    */   private byte type;
/*    */   private String cat;
/*    */   
/*    */   public PacketKnowledgeGain() {}
/*    */   
/*    */   public PacketKnowledgeGain(byte type, String value) {
/* 31 */     this.type = type;
/* 32 */     this.cat = (value == null) ? "" : value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 37 */     buffer.writeByte(this.type);
/* 38 */     ByteBufUtils.writeUTF8String(buffer, this.cat);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 43 */     this.type = buffer.readByte();
/* 44 */     this.cat = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketKnowledgeGain message, MessageContext ctx) {
/* 50 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketKnowledgeGain.this.processMessage(message); } });
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketKnowledgeGain message) {
/* 56 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 57 */     IPlayerKnowledge.EnumKnowledgeType type = IPlayerKnowledge.EnumKnowledgeType.values()[message.type];
/* 58 */     ResearchCategory cat = (message.cat.length() > 0) ? ResearchCategories.getResearchCategory(message.cat) : null;
/* 59 */     RenderEventHandler.INSTANCE; RenderEventHandler.hudHandler.knowledgeGainTrackers.add(new HudHandler.KnowledgeGainTracker(type, cat, 40 + entityPlayerSP.field_70170_p.field_73012_v
/* 60 */           .nextInt(20), entityPlayerSP.field_70170_p.field_73012_v.nextLong()));
/* 61 */     entityPlayerSP.field_70170_p.func_184134_a(entityPlayerSP.field_70165_t, entityPlayerSP.field_70163_u, entityPlayerSP.field_70161_v, SoundsTC.learn, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketKnowledgeGain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */