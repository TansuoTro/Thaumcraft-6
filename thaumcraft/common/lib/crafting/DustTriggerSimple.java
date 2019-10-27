/*    */ package thaumcraft.common.lib.crafting;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.api.crafting.IDustTrigger;
/*    */ import thaumcraft.common.container.InventoryFake;
/*    */ import thaumcraft.common.lib.events.ServerEvents;
/*    */ 
/*    */ public class DustTriggerSimple
/*    */   implements IDustTrigger {
/*    */   Block target;
/*    */   ItemStack result;
/*    */   String research;
/*    */   
/*    */   public DustTriggerSimple(String research, Block target, ItemStack result) {
/* 23 */     this.target = target;
/* 24 */     this.result = result;
/* 25 */     this.research = research;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDustTrigger.Placement getValidFace(World world, EntityPlayer player, BlockPos pos, EnumFacing face) {
/* 30 */     return (world.func_180495_p(pos).func_177230_c() == this.target && (this.research == null || 
/* 31 */       ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research))) ? new IDustTrigger.Placement(0, 0, 0, null) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(final World world, final EntityPlayer player, final BlockPos pos, IDustTrigger.Placement placement, EnumFacing side) {
/* 36 */     FMLCommonHandler.instance().firePlayerCraftingEvent(player, this.result, new InventoryFake(1));
/* 37 */     final IBlockState state = world.func_180495_p(pos);
/* 38 */     ServerEvents.addRunnableServer(world, new Runnable() {
/* 39 */           public void run() { ServerEvents.addSwapper(world, pos, state, DustTriggerSimple.this.result, false, 0, player, true, true, -9999, false, false, 0, ServerEvents.DEFAULT_PREDICATE, 0.0F); }
/*    */         }50);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\DustTriggerSimple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */