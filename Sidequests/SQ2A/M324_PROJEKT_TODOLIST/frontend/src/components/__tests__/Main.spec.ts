import { describe, it, expect, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import Main from '@/components/Main.vue'
import { createPinia, setActivePinia } from 'pinia'

describe('Main component tests', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })
  
  /**
   * Tests if the Main component renders / contains the correct text 
   */
  it('renders properly', () => {
    const wrapper = mount(Main)
    expect(wrapper.find("#title").text()).toStrictEqual("ToDo Liste")
    expect(wrapper.find("#submitBtn").text()).toStrictEqual('Absenden')
  });


  /**
   * Tests if the Main component does not contain the correct content 
   */  
  it("test fails if Element content does not match with actual text", () => {
    const wrapper = mount(Main)
    expect(wrapper.find("#title").text()).toStrictEqual("Todo Liste")
    expect(wrapper.find("#submitBtn").text()).toStrictEqual('ABsenden')
  });

})
