package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mock1;
  private TorpedoStore mock2;
  @BeforeEach
  public void init(){
    mock1 = mock(TorpedoStore.class);
    mock2 = mock(TorpedoStore.class);
    this.ship = new GT4500();
    this.ship.injectDependencies(mock1, mock2, false);
  }
  
  @Test
  public void fireTorpedo_Single_Success(){
    
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
      assertEquals(true, result);
   verify(mock1, times(1)).fire(1);
   verify(mock2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
  
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);
       boolean result = ship.fireTorpedo(FiringMode.ALL);  
  assertEquals(true, result);
   verify(mock1, times(1)).fire(1);
  verify(mock2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Fail(){
        when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);
      boolean result = ship.fireTorpedo(FiringMode.SINGLE);;
      assertEquals(false, result);
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Fail(){
      when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);
       boolean result = ship.fireTorpedo(FiringMode.ALL);
   assertEquals(false, result);
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_Single_Double_Sucess(){
      when(mock1.fire(1)).thenReturn(true);
      when(mock2.isEmpty()).thenReturn(true);
      when(mock1.isEmpty()).thenReturn(false);
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
      assertEquals(true, result1);
    assertEquals(true, result2);
      verify(mock1, times(2)).fire(1);
      verify(mock2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Second_Success(){
    when(mock1.isEmpty()).thenReturn(true);
    when(mock2.isEmpty()).thenReturn(false);
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals(false, result);
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Empty(){
    when(mock1.isEmpty()).thenReturn(true);
    when(mock2.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals(false, result);
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(0)).fire(1);
}
}